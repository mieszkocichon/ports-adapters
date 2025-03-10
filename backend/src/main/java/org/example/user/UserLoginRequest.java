package org.example.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginRequest {
    private @NotBlank @Size(min = 3, max = 255) String username;
    private @NotBlank @Size(min = 3, max = 255) String password;
}
