package com.example.securityProject.service.impl;

import com.example.securityProject.repository.UsuarioRepository;
import com.example.securityProject.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return usuarioRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException(" Usuario no encontrado en la BD"));
            }
        };
    }

}
