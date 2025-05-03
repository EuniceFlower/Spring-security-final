package com.example.securityProject.aggregates.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    private String nombre;
    private String email;
    private String password;
    private String rol;

}
