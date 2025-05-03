package com.example.securityProject.service;

import com.example.securityProject.entity.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsuarioService {
    UserDetailsService userDetailsService();
}
