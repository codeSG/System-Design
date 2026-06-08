package org.example.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import org.example.dtos.AuthenticationDTO.AccessTokenPayloadDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;


import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTokenService {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.access-token-expiration}")
    private Long expiration;

    private SecretKey getSigningKey(){
        //convert secret to byte
        try {
            byte[] bytes = secret.getBytes(StandardCharsets.UTF_8);
            SecretKey signingKey = Keys.hmacShaKeyFor(bytes);
            return signingKey;
        } catch (Exception e){
            throw new RuntimeException("Failed to generate signing key", e);
        }
    }

    public String generateAccessToken(AccessTokenPayloadDTO tokenPayload){
        Map<String,Object> claims = new HashMap<>();
        claims.put("userId",tokenPayload.userId());

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .claims(claims)
                .subject(tokenPayload.email())
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

    public boolean validateAccessToken(String token){
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public Long extractUserId(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.get("userId", Long.class);
    }

    public String extractSubject(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
