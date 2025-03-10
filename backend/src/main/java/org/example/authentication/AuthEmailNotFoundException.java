package org.example.authentication;

public class AuthEmailNotFoundException extends RuntimeException {
    public AuthEmailNotFoundException(String email) {
        super("User name not found with email: " + email);
    }
}
