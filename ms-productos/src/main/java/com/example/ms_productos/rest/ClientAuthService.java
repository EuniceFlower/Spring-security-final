package com.example.ms_productos.rest;

import com.example.ms_productos.response.ResponseValidate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

import java.util.Map;

public interface ClientAuthService {
//@Header("Authorization") String token
    @GET("/apis/codigo/api/auth/validate")
    Call<ResponseValidate> getInfoValidate(@Header("Authorization") String token);
}
