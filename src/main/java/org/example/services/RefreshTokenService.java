package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.Exception.AuthException;
import org.example.dtos.AuthenticationDTO.RefreshTokenPayloadDTO;
import org.example.entities.RefreshToken;
import org.example.repositories.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.HexFormat;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    @Value("${jwt.refresh-token-expiration}")
    private Long Expiry;

    private final RefreshTokenRepository refreshTokenRepository;

    public String generateRefreshToken(){
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().encodeToString(bytes);
    }
    public String hashToken(String token){
        try {
            byte[] bytes = token.getBytes(StandardCharsets.UTF_8);
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(bytes);
            return HexFormat.of().formatHex(hash);
        } catch (Exception e){
            throw new RuntimeException("Failed to validate refresh token", e);
        }
    }

    public boolean validateRefreshToken(String token){
        // hash the token
        String hashedToken = hashToken(token);
        RefreshToken refreshToken = refreshTokenRepository.findByToken(hashedToken)
                .orElseThrow(() ->  new AuthException(HttpStatus.UNAUTHORIZED, "Invalid refresh token"));

        Instant expiry = refreshToken.getExpiresAt();
        if(Instant.now().isAfter(expiry)){
            refreshTokenRepository.delete(refreshToken);
            throw new AuthException(HttpStatus.UNAUTHORIZED, "Refresh token expired");
        }
        return true;
    }

    public RefreshToken saveRefreshTokenToDB(RefreshTokenPayloadDTO refreshTokenPayload){
        String hashedRefreshToken = hashToken(refreshTokenPayload.refreshToken());
        Instant tokenExpiration = Instant.now().plus(Duration.ofDays(Expiry));
        RefreshToken refreshToken = new RefreshToken(refreshTokenPayload.user() ,hashedRefreshToken , tokenExpiration );
        RefreshToken savedRefreshToken = refreshTokenRepository.save(refreshToken);
        return savedRefreshToken;
    }
}
