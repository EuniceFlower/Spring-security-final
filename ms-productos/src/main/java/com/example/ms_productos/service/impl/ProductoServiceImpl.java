package com.example.ms_productos.service.impl;

import com.example.ms_productos.entity.Producto;
import com.example.ms_productos.repository.ProductoRepository;
import com.example.ms_productos.request.RequestProducto;
import com.example.ms_productos.response.ResponseValidate;
import com.example.ms_productos.rest.ClientAuth;
import com.example.ms_productos.rest.ClientAuthService;
import com.example.ms_productos.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;

    @Override
    public Producto createProduct(RequestProducto requestProducto) {
        return Optional.of(productoRepository.save(Producto.builder()
                                .nombre(requestProducto.getNombre())
                                .precio(requestProducto.getPrecio())
                                .categoria(requestProducto.getCategoria())
                                .build())).orElseThrow(() -> new RuntimeException("Error al guardar el producto"));
    }

    @Override
    public List<Producto> ListProduct() {
        try {
        return productoRepository.findAll().stream()
                .map(producto -> Producto.builder()
                        .id(producto.getId())
                        .nombre(producto.getNombre())
                        .precio(producto.getPrecio())
                        .categoria(producto.getCategoria())
                        .build()).toList();
        }catch (Exception e){
            throw new RuntimeException("Error al listar los productos" + e.getMessage());
        }
    }

    @Override
    public Producto updateProduct(RequestProducto producto, Long id) {
        Producto product = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error al buscar el producto"));

        if (product != null) {
            product.setNombre(producto.getNombre());
            product.setPrecio(producto.getPrecio());
            product.setCategoria(producto.getCategoria());
            return productoRepository.save(product);
        } else {
            throw new RuntimeException("Error al actualizar el producto");
        }
    }

    @Override
    public Producto deleteProduct(Long id) {
        Producto product = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error al buscar el producto o no existe"));
            productoRepository.delete(product);
        return product;
    }

}
