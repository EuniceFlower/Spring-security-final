package com.example.ms_productos.Controller;

import com.example.ms_productos.entity.Producto;
import com.example.ms_productos.request.RequestProducto;
import com.example.ms_productos.service.ProductoService;
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
    private ResponseEntity<Producto> create(@RequestBody RequestProducto product) {
        return ResponseEntity.ok(productoService.createProduct(product));
    }

    @GetMapping("/productos")
    private ResponseEntity<List<Producto>> getAll() {
        return ResponseEntity.ok(productoService.ListProduct());
    }
    @PutMapping("/productos/{id}")
    private ResponseEntity<Producto> update(@RequestBody RequestProducto product,
                                            @PathVariable Long id) {
        return ResponseEntity.ok(productoService.updateProduct(product, id));
    }
    @DeleteMapping("/productos/{id}")
    private ResponseEntity<Producto> delete(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.deleteProduct(id));
    }
}
