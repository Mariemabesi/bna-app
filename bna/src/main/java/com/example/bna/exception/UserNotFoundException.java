package com.example.bna.exception;  // The package should match the folder structure

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
