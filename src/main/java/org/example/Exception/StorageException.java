package org.example.Exception;

import lombok.Getter;

@Getter
public class StorageException extends RuntimeException {
    public StorageException(String message) {
        super(message);
    }
}
