package org.example.authentication;

import lombok.RequiredArgsConstructor;
import org.example.adapters.Adapter;
import org.example.common.Factory;
import org.example.user.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Factory
public class AuthSignupRequestToUserEntityAdapter implements Adapter<AuthSignupRequest, UserEntity> {

    private final PasswordEncoder encoder;

    @Override
    public UserEntity map(AuthSignupRequest in) {
        return new UserEntity(in.getUsername(),
                in.getEmail(),
                encoder.encode(in.getPassword()));
    }
}
