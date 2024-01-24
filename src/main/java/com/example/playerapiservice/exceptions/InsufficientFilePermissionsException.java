package com.example.playerapiservice.exceptions;

public class InsufficientFilePermissionsException extends RuntimeException {
    public InsufficientFilePermissionsException(String message) {
        super(message);
    }
}
