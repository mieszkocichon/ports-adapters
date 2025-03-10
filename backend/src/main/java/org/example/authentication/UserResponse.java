package org.example.authentication;

import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.example.user.UserId;
import org.example.user.UserRole;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class UserResponse {
    private @Embedded UserId userId;
    private @NotBlank
    @Size(min = 3, max = 255) String username;
    private @NotBlank @Size(min = 3, max = 255) String email;
    private Set<UserRole> roles = new HashSet<>();
}
