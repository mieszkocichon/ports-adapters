package org.example.authentication;

import lombok.RequiredArgsConstructor;
import org.example.adapters.Adapter;
import org.example.user.User;
import org.example.user.UserRoleToUserRoleResponseAdapter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEntityToUserRegisterResponseAdapter implements Adapter<User, UserResponse> {
    private final UserRoleToUserRoleResponseAdapter roleToUserRoleResponseAdapter;

    @Override
    public UserResponse map(User in) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(in.getUsername());
        userResponse.setUserId(in.getUserId());
        userResponse.setEmail(in.getEmail());
        userResponse.setRoles(roleToUserRoleResponseAdapter.set(in.getRoles()));
        return userResponse;
    }
}
