package com.RevisaAi.JavaProjects.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.RevisaAi.JavaProjects.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenService { 
    
    @Value("${jwt.secret}")
    private String secret;

    public String gerarToken(User user) {

        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                .withIssuer("revisa-ai-api")
                .withSubject(user.getEmail())
                .withExpiresAt(Date.from(LocalDateTime.now().plusMinutes(60).atZone(ZoneId.systemDefault()).toInstant()))
                .sign(algorithm);           
            
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token JWT", exception);
            
        }

    }

    public String validateToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                .withIssuer("revisa-ai-api")
                .build()
                .verify(token)
                .getSubject();
            
        } catch (JWTVerificationException exception) {
            return null;
        }

    }

}