package com.example.ms_ordenes.service;

import com.example.ms_ordenes.entity.Ordenes;
import com.example.ms_ordenes.request.RequestOrden;
import com.example.ms_ordenes.response.BaseResponse;
import com.example.ms_ordenes.response.ResponseOrder;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface OrdenesService {
    ResponseEntity<BaseResponse<ResponseOrder>> createOrden(RequestOrden orden, String token) throws IOException;
    ResponseEntity<BaseResponse<List<Ordenes>>>  listOrden();

}
