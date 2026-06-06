package org.example.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FileValidationException extends RuntimeException {
    private final HttpStatus status;
    public FileValidationException(HttpStatus status , String message){
        super(message);
        this.status = status;
    }
}
