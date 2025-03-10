package org.example.authentication;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.user.UserLoginRequest;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("auth")
public class AuthController {

    private AuthService authService;

    @PostMapping("signin")
    public AuthUserResponse authenticateUser(@Valid @RequestBody UserLoginRequest userLoginRequest) {
        return authService.authenticateUser(userLoginRequest);
    }

    @PostMapping("signup")
    public UserResponse registerUser(@Valid @RequestBody AuthSignupRequest authSignupRequest) {
        return authService.registerUser(authSignupRequest);
    }
}
