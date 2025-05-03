package com.example.securityProject.service;

import com.example.securityProject.entity.Usuario;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {
    String extractUserName(String token);
    String generateToken(UserDetails userDetails, Usuario usuario);
    String extractEmail(String token);
    default boolean validateToken(String token, UserDetails userDetails) {
        return false;
    }

}
