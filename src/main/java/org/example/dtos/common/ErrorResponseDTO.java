package org.example.dtos.common;

public record ErrorResponseDTO(
    String message,
    int status
) {
}