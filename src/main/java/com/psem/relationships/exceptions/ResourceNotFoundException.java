package com.psem.relationships.exceptions;

// Implement my own exception to return message.
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
