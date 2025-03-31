package org.example.authentication;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.common.JwtUtils;
import org.example.user.UserDetailsImpl;
import org.example.user.User;
import org.example.user.UserEmailAlreadyExistsException;
import org.example.user.UserLoginRequest;
import org.example.user.UserRepository;
import org.example.user.UsernameAlreadyExistsException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final AuthSignupRequestToUserAdapter authSignupRequestToUserAdapter;
    private final UserEntityToUserRegisterResponseAdapter userEntityToUserRegisterResponseAdapter;
    private final AuthUserRoleService authUserRoleService;

    @Transactional
    public AuthUserResponse authenticateUser(UserLoginRequest userLoginRequest) {

        userRepository.findByUsername(userLoginRequest.getUsername())
                .orElseThrow(() -> new AuthUsernameNotFoundException(userLoginRequest.getUsername()));

        UsernamePasswordAuthenticationToken authRequest =
                new UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(), userLoginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(authRequest);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        final String userName = userDetails.getUsername();
        final String userEmail = userDetails.getEmail();

        return new AuthUserResponse(jwt, userDetails.getUserId(),
                userName,
                userEmail,
                roles
        );
    }

    @Transactional
    public AuthRegisterUserResponse registerUser(@Valid AuthSignupRequest authSignupRequest) {
        if (userRepository.existsByUsername(authSignupRequest.getUsername())) {
            throw new UsernameAlreadyExistsException(authSignupRequest.getUsername());
        }

        if (userRepository.existsByEmail(authSignupRequest.getEmail())) {
            throw new UserEmailAlreadyExistsException(authSignupRequest.getEmail());
        }

        User user = authSignupRequestToUserAdapter.map(authSignupRequest);
        user.setRoles(authUserRoleService.getInitialUserRoles());
        userRepository.save(user);

        return userEntityToUserRegisterResponseAdapter.map(user);
    }
}
