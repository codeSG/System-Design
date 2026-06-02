package org.example.dtos;

public record AccessTokenPayloadDTO(
    Long userId,
    String email
){};
