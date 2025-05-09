package com.example.ms_ordenes.rest;

import com.example.ms_ordenes.response.ResponseValidate;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ClientAuthService {
    @GET("/apis/codigo/api/auth/validate")
    Call<ResponseValidate> getInfoValidate(@Header("Authorization") String token);
}
