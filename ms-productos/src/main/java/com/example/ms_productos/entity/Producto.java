package com.example.ms_productos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private Double precio;
    private String categoria;
}
