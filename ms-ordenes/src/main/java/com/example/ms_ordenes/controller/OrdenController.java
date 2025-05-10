package com.example.ms_ordenes.controller;

import com.example.ms_ordenes.entity.Ordenes;
import com.example.ms_ordenes.request.RequestOrden;
import com.example.ms_ordenes.response.BaseResponse;
import com.example.ms_ordenes.response.ResponseOrder;
import com.example.ms_ordenes.service.OrdenesService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/ordenes/v1/")
public class OrdenController {

    private final OrdenesService ordenesService;

    @PostMapping("/create")
    private ResponseEntity<BaseResponse<ResponseOrder>> createOrden(@Valid @RequestBody RequestOrden orden,
                                                                   @RequestHeader("Authorization") String token) throws IOException {
        return ordenesService.createOrden(orden, token);
    }

    @GetMapping("/all")
    private ResponseEntity<BaseResponse<List<Ordenes>>> getAll() {
        return ordenesService.listOrden();
    }
}
