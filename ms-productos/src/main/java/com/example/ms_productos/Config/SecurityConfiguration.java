package com.example.ms_productos.Config;

import com.example.ms_productos.constants.Rol;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
        private final JwtAuthenticationFilter jwtAuthenticationFilter;
        private final String urls = "/api/productos/v1/**";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request.requestMatchers(HttpMethod.POST, urls).hasAnyAuthority(Rol.ADMIN.name(), Rol.SUPERADMIN.name())
                                .requestMatchers(HttpMethod.PUT, urls).hasAnyAuthority(Rol.ADMIN.name(), Rol.SUPERADMIN.name())
                                .requestMatchers(HttpMethod.DELETE, urls).hasAnyAuthority(Rol.ADMIN.name(), Rol.SUPERADMIN.name())
                                .anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();

    }



}
