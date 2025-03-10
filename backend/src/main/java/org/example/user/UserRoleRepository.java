package org.example.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole getUserRoleByRoleName(RoleName roleName);
}
