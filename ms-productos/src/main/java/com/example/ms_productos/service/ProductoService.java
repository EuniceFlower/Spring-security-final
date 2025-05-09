package com.example.ms_productos.service;

import com.example.ms_productos.entity.Producto;
import com.example.ms_productos.request.RequestProducto;
import com.example.ms_productos.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import retrofit2.Response;

import java.util.List;

public interface ProductoService {
    ResponseEntity<BaseResponse<Producto>> createProduct(RequestProducto producto);
    ResponseEntity<BaseResponse<List<Producto>>>  ListProduct();
    ResponseEntity<BaseResponse<Producto>> updateProduct(RequestProducto producto, Long id);
    ResponseEntity<BaseResponse<Producto>> deleteProduct(Long id);


}
