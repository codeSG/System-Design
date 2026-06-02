package org.example.Exception;

import org.example.dtos.ErrorResponseDTO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AuthException.class)
    public ErrorResponseDTO handleAuthException(AuthException ex){
        return new ErrorResponseDTO(ex.getMessage(), ex.getStatus().value());
    }
}
