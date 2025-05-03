package com.example.securityProject.service.impl;

import com.example.securityProject.aggregates.constants.Constants;
import com.example.securityProject.entity.Usuario;
import com.example.securityProject.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtServiceImpl implements JwtService {
    @Value("${key.signature}")
    private String keySignature;
    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public String extractEmail(String token) {
        return extractClaim(token, claims -> claims.get("email", String.class));
    }

    @Override
    public String generateToken(UserDetails userDetails, Usuario usuario) {
        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setClaims(addClaims(userDetails))
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 60000))
                .claim("type", Constants.ACCESS)
                .claim("id", usuario.getId())
                .claim("Nombre",usuario.getNombre())
                .claim("email",usuario.getEmail())
                .claim("rol", usuario.getRol())
                .signWith(getSignKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername())
                && !isTokenExpired(token));
    }

    //METODO QUE DECODIFICA LA LLAVE A SU FORMA NATURAL
    private Key getSignKey(){
        byte[] key = Decoders.BASE64.decode(keySignature);
        return Keys.hmacShaKeyFor(key);
    }
    //METODO PARA EXTRAER EL PAYLOAD(CLAIMS) DEL TOKEN
    private Claims extractAllClaims(String  token){
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build()
                .parseClaimsJws(token).getBody();
    }

    //METODO PAR AOBTENER UN ELEMNTO(ATRIBUTO ) DEL PAYLOAD (CLAIM)
    private <T> T extractClaim(String token, Function<Claims,T> claimsTFunction){
        return claimsTFunction.apply(extractAllClaims(token));
    }

    //METODO PARA VALIDAR SI EL TOKEN ESTA EXPIRADO (TRUE == EXPIRO | FALSE == TOKEN VALIDO)
    private boolean isTokenExpired(String token){
        return extractClaim(token,Claims::getExpiration).before(new Date());
    }
    private Map<String, Object> addClaims(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.CLAVE_AccountNonLocked, userDetails.isAccountNonLocked());
        claims.put(Constants.CLAVE_AccountNonExpired, userDetails.isAccountNonExpired());
        claims.put(Constants.CLAVE_CredentialsNonExpired, userDetails.isCredentialsNonExpired());
        claims.put(Constants.CLAVE_Enabled, userDetails.isEnabled());
        claims.put("TypeUser", userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("NOT-VALUE"));

        return claims;
    }
}
