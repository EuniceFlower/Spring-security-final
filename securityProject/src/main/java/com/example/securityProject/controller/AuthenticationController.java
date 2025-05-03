package com.example.securityProject.controller;

import com.example.securityProject.aggregates.request.SignInRequest;
import com.example.securityProject.aggregates.request.SignUpRequest;
import com.example.securityProject.aggregates.response.SignInResponse;
import com.example.securityProject.entity.Usuario;
import com.example.securityProject.service.AuthenticationService;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.Base64;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Usuario> signUpUser(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signUpUser(signUpRequest));
    }
//esto no, este va a servir para generar mi key que pegarpe en propertie key.signature
    @GetMapping("/clave")
    public ResponseEntity<String> getClave(){
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String dato = Base64.getEncoder().encodeToString(key.getEncoded());
        return ResponseEntity.ok(dato);
    }

    @PostMapping("/login")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }

    @GetMapping("/validate")
    public ResponseEntity<UserDetails> validateToken(){
        return ResponseEntity.ok(authenticationService.validateUserToken());
    }


}
