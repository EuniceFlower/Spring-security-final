package com.example.securityProject.service.impl;

import com.example.securityProject.aggregates.constants.Constants;
import com.example.securityProject.aggregates.request.SignInRequest;
import com.example.securityProject.aggregates.request.SignUpRequest;
import com.example.securityProject.aggregates.response.BaseResponse;
import com.example.securityProject.aggregates.response.SignInResponse;
import com.example.securityProject.entity.Rol;
import com.example.securityProject.entity.Usuario;
import com.example.securityProject.repository.UsuarioRepository;
import com.example.securityProject.service.AuthenticationService;
import com.example.securityProject.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Override
    public ResponseEntity<BaseResponse<Usuario>> signUpUser(SignUpRequest signUpRequest) {
        BaseResponse<Usuario> response;

        if (!usuarioRepository.existsByEmail(signUpRequest.getEmail())) {
            Usuario usuario = getUsuarioEntity(signUpRequest);
            Rol rol = Optional.of(Rol.valueOf(signUpRequest.getRol().toUpperCase()))
                    .orElseThrow(() -> new IllegalArgumentException("Rol no válido"));
            usuario.setRol(rol);

            response = BaseResponse.<Usuario>builder()
                    .code(Constants.CODE_OK)
                    .message(Constants.MSJ_OK)
                    .data(Optional.of(usuarioRepository.save(usuario)))
                    .build();
        } else {
            response = BaseResponse.<Usuario>builder()
                    .code(Constants.CODE_EXIST)
                    .message(Constants.MSJ_DUPLICATED)
                    .build();
        }
        return ResponseEntity.ofNullable(response);
    }

    @Override
    public Usuario signUpAdmin(SignUpRequest signUpRequest) {
        Usuario usuario = getUsuarioEntity(signUpRequest);
        return usuarioRepository.save(usuario);
    }

    @Override
    public SignInResponse signIn(SignInRequest signInRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getEmail(),signInRequest.getPassword()));

        var usuario = usuarioRepository.findByEmail(signInRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado en la BD"));

        var token = jwtService.generateToken(usuario,usuario);

        return SignInResponse.builder()
                .accessToken(token)
                .build();
    }
    @Override
    public UserDetails validateUserToken() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


    //GENERAR UN ACCESS TOKEN A PARTIR DE UN REFRESHTOKEN


    private Usuario getUsuarioEntity(SignUpRequest signUpRequest){
        return Usuario.builder()
                .nombre(signUpRequest.getNombre())
                .email(signUpRequest.getEmail())
                .password(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()))
                .isAccountNonExpired(Constants.STATUS_ACTIVE)
                .isAccountNonLocked(Constants.STATUS_ACTIVE)
                .isCredentialsNonExpired(Constants.STATUS_ACTIVE)
                .isEnabled(Constants.STATUS_ACTIVE)
                .build();
    }

}
