package com.example.ms_productos.service.impl;

import com.example.ms_productos.entity.Producto;
import com.example.ms_productos.repository.ProductoRepository;
import com.example.ms_productos.request.RequestProducto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class ProductoServiceImplTest {

    // Mock the dependencies
    @Mock
    private ProductoRepository productoRepository;
    @InjectMocks
    private ProductoServiceImpl productoServiceImpl;

    private RequestProducto requestProducto;
    private Producto producto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize the mocks before each test
        requestProducto = new RequestProducto();
        requestProducto.setNombre("El conde de Montecristo");
        requestProducto.setPrecio(1000.0);
        requestProducto.setCategoria("tec");
    }

    @Test
    void createProduct() {
        //arrange

    }

    @Test
    void listProduct() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }
}