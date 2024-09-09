package com.arquitetura.senac.config;

import com.arquitetura.senac.dto.UsuarioRequest;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(UsuarioRequest request) {
       try {
           Algorithm algorithm = Algorithm.HMAC256(secret);
           return JWT.create()
                   .withIssuer("senac-api")
                   .withSubject(request.email())
                   .withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")))
                   .sign(algorithm);
       } catch (JWTCreationException e) {
            throw new RuntimeException("Error while generating token", e);
       }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("senac-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return "";
        }
    }
}
