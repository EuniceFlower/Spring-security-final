package com.example.ms_ordenes.service.impl;

import com.example.ms_ordenes.entity.Ordenes;
import com.example.ms_ordenes.repository.OrdenRepository;
import com.example.ms_ordenes.request.RequestOrden;
import com.example.ms_ordenes.response.ResponseOrder;
import com.example.ms_ordenes.response.ResponseProduct;
import com.example.ms_ordenes.response.ResponseValidate;
import com.example.ms_ordenes.rest.ClientProductService;
import com.example.ms_ordenes.rest.ClientProductos;
import com.example.ms_ordenes.service.OrdenesService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// cual es la diferencia entre requiredargsconstructor y allargsconstructor
@RequiredArgsConstructor
@Service
public class OrdenesServiceImpl implements OrdenesService {

    private final OrdenRepository ordenRepository;

    ClientProductService clientProductService = ClientProductos.getRetrofit().create(ClientProductService.class);

    @Override
    public ResponseOrder createOrden(RequestOrden orden, String token) throws IOException {
        Long id = ((ResponseValidate) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();

        List<ResponseProduct> responseProduct = executeProduct(token);

        //Productos con detalles
        Set<ResponseProduct> products = productsDetails(responseProduct, orden);

        Set<Long> productIds = responseProduct.stream()
                .filter(product -> orden.getIdProductos().contains(product.getId()))
                .map(ResponseProduct::getId)
                .collect(Collectors.toSet());

        if (products.size() == orden.getIdProductos().size()) {
            Ordenes orderFinally = ordenRepository.save(Ordenes.builder()
                    .usuarioId(id)
                    .productosIds(productIds)
                    .fecha(LocalDateTime.now())
                    .build());

            return ResponseOrder.builder()
                    .id(orderFinally.getId())
                    .idClient(orderFinally.getUsuarioId())
                    .products(products)
                    .createdDate(orderFinally.getFecha())
                    .build();
        }
        throw new RuntimeException("Error al crear la orden, no se encontraron todos los productos");
    }


    @Override
    public List<Ordenes> listOrden() {
        try {

            return ordenRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar las ordenes" + e.getMessage());
        }
    }

    private List<ResponseProduct> executeProduct(String token) throws IOException {
        List<ResponseProduct> responseProduct = null;
        Response<List<ResponseProduct>> response = executeRetrofit(token).execute();

        if (response.isSuccessful() && response.body() != null) {
            responseProduct = response.body();
        } else {
            throw new RuntimeException("Error al obtener los productos");
        }
        return responseProduct;
    }

    private Call<List<ResponseProduct>> executeRetrofit(String token) {
        return clientProductService.getProduct(token);
    }

    private Set<ResponseProduct> productsDetails(List<ResponseProduct> responseProduct, RequestOrden orden) {
        return responseProduct.stream()
                .filter(product -> orden.getIdProductos().contains(product.getId()))
                .collect(Collectors.toSet());
    }
}
