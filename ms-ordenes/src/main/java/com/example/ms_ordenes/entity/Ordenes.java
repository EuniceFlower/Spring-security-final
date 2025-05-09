package com.example.ms_ordenes.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ordenes")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ordenes {
    @Id
    @GeneratedValue
    private Long id;
    //ESte id lo puedo sacar del token
    private Long usuarioId;
    private Set<Long> productosIds;
    private LocalDateTime fecha;

}
