package org.example.dtos.AuthenticationDTO;

public record SignupResponseDTO(Long userId,
        String accessToken,
        String refreshToken, String message) { }
