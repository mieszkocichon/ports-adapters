package org.example.authentication;

import lombok.RequiredArgsConstructor;
import org.example.user.RoleName;
import org.example.user.UserRole;
import org.example.user.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class AuthUserRoleService {
    private final UserRoleRepository userRoleRepository;

    public Set<UserRole> getInitialUserRoles() {
        return Set.of(userRoleRepository.getUserRoleByRoleName(RoleName.ROLE_USER));
    }
}
