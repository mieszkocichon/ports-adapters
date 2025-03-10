package org.example.authentication;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AuthUsernameNotFoundException extends RuntimeException {
    public AuthUsernameNotFoundException(String username) {
        super("User name not found with name: " + username);
    }
}
