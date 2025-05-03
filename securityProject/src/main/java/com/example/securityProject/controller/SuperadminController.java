package com.example.securityProject.controller;

import com.example.securityProject.entity.Rol;
import com.example.securityProject.entity.Usuario;
import com.example.securityProject.service.RolesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/test/superadmin/")
public class SuperadminController {
    private final RolesService rolesService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getSuperadmin(){
        return ResponseEntity.ok(rolesService.getAll(Rol.SUPERADMIN));
    }
}
