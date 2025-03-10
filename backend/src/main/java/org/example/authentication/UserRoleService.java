package org.example.authentication;

import org.example.user.ERole;
import org.example.user.UserRole;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserRoleService {
    public Set<UserRole> getRole(AuthSignupRequest authSignupRequest) {
        Set<String> strRoles = authSignupRequest.getRoles();
        Set<UserRole> roles = new HashSet<>();

        if (strRoles == null) {
            roles.add(new UserRole(ERole.ROLE_USER));
        }
        else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        UserRole adminRole = new UserRole(ERole.ROLE_ADMIN);
                        roles.add(adminRole);

                        break;
                    case "mod":
                        UserRole modRole = new UserRole(ERole.ROLE_MODERATOR);
                        roles.add(modRole);

                        break;
                    default:
                        UserRole userRole = new UserRole(ERole.ROLE_USER);
                        roles.add(userRole);
                }
            });
        }

        return roles;
    }
}
