package com.example.ms_ordenes.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data
public class ResponseProduct {
    private Long id;
    private String nombre;
    private Double precio;
    private String categoria;
}
