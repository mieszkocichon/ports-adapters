package org.example.authentication;

import lombok.RequiredArgsConstructor;
import org.example.adapters.Adapter;
import org.example.common.Factory;
import org.example.user.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@RequiredArgsConstructor
@Factory
public class AuthSignupRequestToUserAdapter implements Adapter<AuthSignupRequest, User> {

    private final PasswordEncoder encoder;

    @Override
    public User map(AuthSignupRequest in) {
        User user = new User();
        user.setUuid(UUID.randomUUID());
        user.setUsername(in.getUsername());
        user.setPassword(encoder.encode(in.getPassword()));
        user.setEmail(in.getEmail());
        return user;
    }
}
