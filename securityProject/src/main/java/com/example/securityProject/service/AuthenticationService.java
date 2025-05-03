package com.example.securityProject.service;

import com.example.securityProject.aggregates.request.SignInRequest;
import com.example.securityProject.aggregates.request.SignUpRequest;
import com.example.securityProject.aggregates.response.SignInResponse;
import com.example.securityProject.entity.Usuario;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface AuthenticationService {
    //Registrar Usuario
    Usuario signUpUser(SignUpRequest signUpRequest);
    //Registrar Admin
    Usuario signUpAdmin(SignUpRequest signUpRequest);
    //Metodo para login
    SignInResponse signIn(SignInRequest signInRequest);
    //Metodo para obtener un nuevo accesstoken a partir del refreshtoken
    //SignInResponse getTokenByRefreshToken(String refreshToken) throws IllegalAccessException;
    UserDetails validateUserToken();
}
