package org.example.dtos.AuthenticationDTO;

import org.example.entities.User;

public record RefreshTokenPayloadDTO (
        User user,
        String refreshToken
){};
