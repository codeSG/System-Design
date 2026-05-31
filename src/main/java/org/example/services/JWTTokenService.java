package org.example.services;

import io.jsonwebtoken.security.Keys;
import org.example.dtos.TokenPayloadDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTTokenService {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.access-token-expiration}")
    private Long expiration;

    private SecretKey getSigningKey(){
        //convert secret to byte
        byte[] bytes = secret.getBytes(StandardCharsets.UTF_8);
        SecretKey signingKey = Keys.hmacShaKeyFor(bytes);
        return signingKey;
    }

    public String generateAccessToken(TokenPayloadDTO tokenPayload){
        Map<String,Object> claims = new HashMap<>();
        claims.put("userId",tokenPayload.userId());
        


    }
}
