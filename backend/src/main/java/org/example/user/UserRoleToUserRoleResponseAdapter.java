package org.example.user;

import org.example.adapters.Adapter;
import org.springframework.stereotype.Component;

@Component
public class UserRoleToUserRoleResponseAdapter implements Adapter<UserRole, UserRoleResponse> {
    @Override
    public UserRoleResponse map(UserRole in) {
        UserRoleResponse response = new UserRoleResponse();
        response.setRoleName(in.getRoleName().name());
        return response;
    }
}
