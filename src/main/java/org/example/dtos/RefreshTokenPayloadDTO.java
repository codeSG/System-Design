package org.example.dtos;

import org.example.entities.User;

public record RefreshTokenPayloadDTO (
        User user,
        String refreshToken
){};
