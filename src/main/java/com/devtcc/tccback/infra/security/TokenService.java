package com.devtcc.tccback.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.devtcc.tccback.entities.Usuario;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String secret;
	
	public String generateToken(Usuario usuario){
		
		try {
			
			Algorithm algorithm = Algorithm.HMAC256(secret);
			
			String token = JWT.create().withIssuer("tccback")
					.withSubject(usuario.getEmail())
					.withExpiresAt(this.generateExpirationDate())
					.sign(algorithm);	
			
			return token;
		}catch(JWTCreationException exception) {
			throw new RuntimeException("Erro na autenticação");
		}
		
	}
	
	 public String validateToken(String token){
	        try {
	            Algorithm algorithm = Algorithm.HMAC256(secret);
	            return JWT.require(algorithm)
	                    .withIssuer("tccback")
	                    .build()
	                    .verify(token)
	                    .getSubject();
	        } catch (JWTVerificationException exception) {
	            return "";
	        }
	    }
	
	private Instant generateExpirationDate() {
		return LocalDateTime.now().plusYears(1).toInstant(ZoneOffset.of("-03:00"));//.plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
	
}
