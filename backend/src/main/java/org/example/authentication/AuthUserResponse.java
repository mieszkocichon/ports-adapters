package org.example.authentication;

import lombok.*;
import org.example.user.UserId;

import java.util.List;

@Builder
@Getter
@ToString(of = {"token", "type", "userId", "username", "email", "roles"})
@EqualsAndHashCode(of = "userId")
public class AuthUserResponse {

    private final String token;
    private final String type = "Bearer";
    private final UserId userId;
    private final String username;
    private final String email;
    private final List<String> roles;

    public AuthUserResponse(String accessToken,
                            UserId userId,
                            String username,
                            String email,
                            List<String> roles) {
        this.token = accessToken;
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
