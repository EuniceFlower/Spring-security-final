package com.example.ms_ordenes.rest;

import com.example.ms_ordenes.response.BaseResponse;
import com.example.ms_ordenes.response.ResponseProduct;
import com.example.ms_ordenes.response.ResponseValidate;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

import java.util.List;

public interface ClientProductService {
    @GET("/api/productos/v1/productos")
    Call<List<ResponseProduct>> getProduct(@Header("Authorization") String token);
}
