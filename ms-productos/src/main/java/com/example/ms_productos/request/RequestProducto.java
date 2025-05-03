package com.example.ms_productos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RequestProducto {
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotNull(message = "El precio no puede estar vacío")
    @Positive(message = "El precio debe ser mayor a cero")
    private Double precio;
    @NotBlank(message = "La categoría es obligatoria")
    private String categoria;
}
