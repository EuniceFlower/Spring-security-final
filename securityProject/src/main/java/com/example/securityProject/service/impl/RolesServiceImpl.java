package com.example.securityProject.service.impl;

import com.example.securityProject.entity.Rol;
import com.example.securityProject.entity.Usuario;
import com.example.securityProject.repository.UsuarioRepository;
import com.example.securityProject.service.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RolesServiceImpl implements RolesService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> getAll(Rol rol) {
        return usuarioRepository.findByRol(rol)
                .orElseThrow(() -> new RuntimeException("No se encontraron usuarios con rol"));
    }

}





