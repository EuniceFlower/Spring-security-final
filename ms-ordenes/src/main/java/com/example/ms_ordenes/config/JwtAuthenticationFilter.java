package com.example.ms_ordenes.config;

import com.example.ms_ordenes.response.ResponseValidate;
import com.example.ms_ordenes.rest.ClientAuth;
import com.example.ms_ordenes.rest.ClientAuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    ClientAuthService clientAuthService = ClientAuth.getRetrofit().create(ClientAuthService.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String tokenExtraidoHeader = request.getHeader("Authorization");

        if(SecurityContextHolder.getContext().getAuthentication() == null){
            //Definir un contexto de seguridad vacio;
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

            //Valido el token consultando al servicio auth
            ResponseValidate responseValidate = responseExecute(tokenExtraidoHeader);

            //Validación del token
            if(responseValidate != null){
                //Objeto que representa al usuario autenticado
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(responseValidate,
                                null, responseValidate.getAuthorities());

                //Agregamos ldetalles adicionales del reqeuest sobre el objeto de autenticación
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //Establecemos el contexto de seguridad creado anteriormente como vacio
                securityContext.setAuthentication(authenticationToken);

                //Pasando el contexto creado al global
                SecurityContextHolder.setContext(securityContext);
            }

        }

        //COntinuar con el resto de filtros
        filterChain.doFilter(request,response);
    }

    private ResponseValidate responseExecute(String tokenExtraidoHeader) throws IOException {
        ResponseValidate responseValidate = null;
        Response<ResponseValidate> response = executeRetrofit(tokenExtraidoHeader).execute();


        if (response.isSuccessful() && response.body() != null) {
            responseValidate = response.body();
        }
        return responseValidate;
    }

    private Call<ResponseValidate> executeRetrofit(String tokenExtraidoHeader) {
        return clientAuthService.getInfoValidate(tokenExtraidoHeader);
    }
}
