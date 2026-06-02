package org.example.dtos;

public record ErrorResponseDTO(
    String message,
    int status
) {
}