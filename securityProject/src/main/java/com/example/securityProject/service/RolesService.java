package com.example.securityProject.service;
import com.example.securityProject.entity.Rol;
import com.example.securityProject.entity.Usuario;

import java.util.List;

public interface RolesService {
    List<Usuario> getAll(Rol rol);
}
