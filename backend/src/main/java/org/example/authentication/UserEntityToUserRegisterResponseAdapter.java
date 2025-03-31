package org.example.authentication;

import lombok.RequiredArgsConstructor;
import org.example.adapters.Adapter;
import org.example.user.User;
import org.example.user.UserRoleToUserRoleResponseAdapter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEntityToUserRegisterResponseAdapter implements Adapter<User, AuthRegisterUserResponse> {
    private final UserRoleToUserRoleResponseAdapter roleToUserRoleResponseAdapter;

    @Override
    public AuthRegisterUserResponse map(User in) {
        AuthRegisterUserResponse authRegisterUserResponse = new AuthRegisterUserResponse();
        authRegisterUserResponse.setUsername(in.getUsername());
        authRegisterUserResponse.setUserId(in.getUserId());
        authRegisterUserResponse.setEmail(in.getEmail());
        authRegisterUserResponse.setRoles(roleToUserRoleResponseAdapter.set(in.getRoles()));
        return authRegisterUserResponse;
    }
}
