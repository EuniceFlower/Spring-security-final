package com.example.ms_ordenes.repository;

import com.example.ms_ordenes.entity.Ordenes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenRepository extends JpaRepository<Ordenes, Long> {
}
