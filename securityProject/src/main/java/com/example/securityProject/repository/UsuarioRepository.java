package com.example.securityProject.repository;

import com.example.securityProject.entity.Rol;
import com.example.securityProject.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Boolean existsByEmail(String email);
    Optional<Usuario> findByEmail(String email);
    Optional<List<Usuario>> findByRol(Rol rol);
}
