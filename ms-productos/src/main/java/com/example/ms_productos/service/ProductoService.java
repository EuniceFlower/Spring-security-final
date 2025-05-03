package com.example.ms_productos.service;

import com.example.ms_productos.entity.Producto;
import com.example.ms_productos.request.RequestProducto;

import java.util.List;

public interface ProductoService {
    Producto createProduct(RequestProducto producto);
    List<Producto> ListProduct();
    Producto updateProduct(RequestProducto producto, Long id);
    Producto deleteProduct(Long id);


}
