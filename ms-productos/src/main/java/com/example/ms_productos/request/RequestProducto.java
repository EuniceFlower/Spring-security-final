package com.example.ms_productos.request;

import lombok.Data;

@Data
public class RequestProducto {
    private String nombre;
    private Double precio;
    private String categoria;
}
