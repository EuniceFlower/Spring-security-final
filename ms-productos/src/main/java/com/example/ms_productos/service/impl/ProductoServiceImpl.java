package com.example.ms_productos.service.impl;

import com.example.ms_productos.constants.Constants;
import com.example.ms_productos.entity.Producto;
import com.example.ms_productos.repository.ProductoRepository;
import com.example.ms_productos.request.RequestProducto;
import com.example.ms_productos.response.BaseResponse;
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
    public ResponseEntity<BaseResponse<Producto>> createProduct(RequestProducto requestProducto) {
        boolean exists = productoRepository.existsByNombre(requestProducto.getNombre());

        if (exists) {
            return ResponseEntity.ofNullable(BaseResponse.<Producto>builder()
                    .code(Constants.CODE_EXIST)
                    .message(Constants.MSJ_EXIST)
                    .build());
        } else {
            Producto producto = productoRepository.save(Producto.builder()
                    .nombre(requestProducto.getNombre())
                    .precio(requestProducto.getPrecio())
                    .categoria(requestProducto.getCategoria())
                    .build());
            return ResponseEntity.ofNullable(BaseResponse.<Producto>builder()
                    .code(Constants.CODE_OK)
                    .message(Constants.MSJ_OK)
                    .data(Optional.of(producto))
                    .build());
        }
    }

    @Override
    public ResponseEntity<BaseResponse<List<Producto>>> ListProduct() {
        List<Producto> productos = productoRepository.findAll();
        BaseResponse<List<Producto>> response;

        if (!productos.isEmpty()) {
            response = BaseResponse.<List<Producto>>builder()
                    .code(Constants.CODE_OK)
                    .message(Constants.MSJ_OK)
                    .data(Optional.of(productos))
                    .build();
        } else {
             response = BaseResponse.<List<Producto>>builder()
                    .code(Constants.CODE_EXIST)
                    .message(Constants.MSJ_EMPTY)
                    .data(Optional.empty())
                    .build();
        }
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<BaseResponse<Producto>> updateProduct(RequestProducto requestProducto, Long id) {
        BaseResponse<Producto> response;

        if (productoRepository.existsById(id)) {
            Producto product = productoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Error al buscar el producto"));

            Producto update = setUpdate(product, requestProducto);
            response = BaseResponse.<Producto>builder()
                    .code(Constants.CODE_OK)
                    .message(Constants.MSJ_OK)
                    .data(Optional.of(productoRepository.save(update)))
                    .build();
        } else {
            response = BaseResponse.<Producto>builder()
                    .code(Constants.CODE_EXIST)
                    .message(Constants.MSJ_EMPTY)
                    .data(Optional.empty())
                    .build();
        }
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<BaseResponse<Producto>> deleteProduct(Long id) {
        BaseResponse<Producto> response;

        if (productoRepository.existsById(id)) {
            Producto product = productoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Error al buscar el producto"));
            productoRepository.delete(product);
            response = BaseResponse.<Producto>builder()
                    .code(Constants.CODE_OK)
                    .message(Constants.MSJ_DELETE)
                    .data(Optional.of(product))
                    .build();
        } else {
            response = BaseResponse.<Producto>builder()
                    .code(Constants.CODE_EXIST)
                    .message(Constants.MSJ_EMPTY)
                    .data(Optional.empty())
                    .build();

        }
        return ResponseEntity.ok(response);
    }

    private Producto setUpdate(Producto producto, RequestProducto requestProducto) {
        producto.setNombre(requestProducto.getNombre());
        producto.setPrecio(requestProducto.getPrecio());
        producto.setCategoria(requestProducto.getCategoria());
        return producto;
    }

}
