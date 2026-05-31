package org.example.dtos;

public record SignupResponseDTO(Long userId,
        String accessToken,
        String refreshToken, String message) { }
