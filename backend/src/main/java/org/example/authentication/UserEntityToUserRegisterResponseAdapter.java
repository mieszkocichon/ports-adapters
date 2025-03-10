package org.example.authentication;

import org.example.adapters.Adapter;
import org.example.user.UserEntity;

public class UserEntityToUserRegisterResponseAdapter implements Adapter<UserEntity, UserResponse> {
    @Override
    public UserResponse map(UserEntity in) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(in.getUsername());
        userResponse.setUserId(in.getUserId());
        userResponse.setEmail(in.getEmail());
        userResponse.setRoles(in.getRoles());
        return userResponse;
    }
}
