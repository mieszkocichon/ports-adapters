package org.example.authentication;

import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.example.user.UserId;
import org.example.user.UserRole;
import org.example.user.UserRoleResponse;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class UserResponse {
    private UserId userId;
    private String username;
    private String email;
    private Set<UserRoleResponse> roles = new HashSet<>();
}
