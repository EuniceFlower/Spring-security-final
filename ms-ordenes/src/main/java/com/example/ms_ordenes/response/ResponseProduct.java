package com.example.ms_ordenes.response;

import lombok.Data;

@Data
public class ResponseProduct {
    private Long id;
    private String nombre;
    private Double precio;
    private String categoria;
}
