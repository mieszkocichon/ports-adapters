package org.example.authentication;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.common.JwtUtils;
import org.example.user.UserDetailsImpl;
import org.example.user.UserEntity;
import org.example.user.UserLoginRequest;
import org.example.user.UserRepository;
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
    private final AuthSignupRequestToUserEntityAdapter authSignupRequestToUserEntityAdapter;
    private final UserEntityToUserRegisterResponseAdapter userEntityToUserRegisterResponseAdapter;
    private final UserRoleService userRoleService;

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
    public UserResponse registerUser(@Valid AuthSignupRequest authSignupRequest) {

        userRepository.findByUsername(authSignupRequest.getUsername())
                .orElseThrow(() -> new AuthUsernameNotFoundException(authSignupRequest.getUsername()));

        userRepository.findByEmail(authSignupRequest.getEmail())
                .orElseThrow(() -> new AuthEmailNotFoundException(authSignupRequest.getEmail()));

        UserEntity userEntity = authSignupRequestToUserEntityAdapter.map(authSignupRequest);
        userEntity.setRoles(userRoleService.getRole(authSignupRequest));
        userRepository.save(userEntity);

        return userEntityToUserRegisterResponseAdapter.map(userEntity);
    }
}
