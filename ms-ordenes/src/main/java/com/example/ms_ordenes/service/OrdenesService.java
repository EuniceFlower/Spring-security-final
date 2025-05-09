package com.example.ms_ordenes.service;

import com.example.ms_ordenes.entity.Ordenes;
import com.example.ms_ordenes.request.RequestOrden;
import com.example.ms_ordenes.response.ResponseOrder;

import java.io.IOException;
import java.util.List;

public interface OrdenesService {
    ResponseOrder createOrden(RequestOrden orden, String token) throws IOException;
    List<Ordenes> listOrden();

}
