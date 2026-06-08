package org.example.dtos.AuthenticationDTO;

public record AccessTokenPayloadDTO(
    Long userId,
    String email
){};
