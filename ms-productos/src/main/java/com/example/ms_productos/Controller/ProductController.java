package com.example.ms_productos.Controller;

import com.example.ms_productos.entity.Producto;
import com.example.ms_productos.request.RequestProducto;
import com.example.ms_productos.response.BaseResponse;
import com.example.ms_productos.service.ProductoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.GET;

import java.util.List;

@RestController
@RequestMapping("/api/productos/v1/")
@AllArgsConstructor
public class ProductController {

    private final ProductoService productoService;


    @PostMapping("/create")
    private ResponseEntity<BaseResponse<Producto>> create(@Valid @RequestBody RequestProducto product) {
        return productoService.createProduct(product);
    }

    @GetMapping("/productos")
    private ResponseEntity<BaseResponse<List<Producto>>> getAll() {
        return productoService.ListProduct();
    }
    @PutMapping("/productos/{id}")
    private ResponseEntity<BaseResponse<Producto>> update(@Valid @RequestBody RequestProducto product,
                                            @PathVariable Long id) {
        return productoService.updateProduct(product, id);
    }
    @DeleteMapping("/productos/{id}")
    private ResponseEntity<BaseResponse<Producto>> delete(@PathVariable Long id) {
        return productoService.deleteProduct(id);
    }


}
