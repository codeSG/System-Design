package org.example.Exception;

import org.example.dtos.common.ErrorResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorResponseDTO> handleAuthException(AuthException ex){
        ErrorResponseDTO error = new ErrorResponseDTO(
                ex.getMessage(),
                ex.getStatus().value());

        return ResponseEntity
                .status(ex.getStatus())
                .body(error);
    }

    @ExceptionHandler(FileValidationException.class)
    public ResponseEntity<ErrorResponseDTO> handleFileValidationException(FileValidationException ex){
        ErrorResponseDTO error = new ErrorResponseDTO(
                ex.getMessage(),
                ex.getStatus().value());

        return ResponseEntity
                .status(ex.getStatus())
                .body(error);
    }
}
