package com.example.securityProject.aggregates.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class SignUpRequest {
    @NotBlank(message = "El nombre no puede estar vacío")
    @NotNull(message = "El nombre no puede ser nulo")
    private String nombre;
    @NotBlank(message = "El apellido no puede estar vacío")
    @NotNull(message = "El apellido no puede ser nulo")
    private String email;
    @NotBlank(message = "La contraseña no puede estar vacía")
    @NotNull(message = "La contraseña no puede ser nula")
    private String password;
    @NotBlank(message = "El rol no puede estar vacío")
    @NotNull(message = "El rol no puede ser nulo")
    private String rol;

}
