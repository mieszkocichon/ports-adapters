package org.example.userRole;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.example.common.BaseEntity;
import org.example.user.ERole;
import org.example.user.UserEntity;
import org.example.user.UserRoleEntity;

@RequiredArgsConstructor
@Entity
@Table(name = "user_roles")
public class UserRole extends BaseEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;

    @Id
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    @Enumerated(EnumType.STRING)  // Store enum as string
    private ERole name;
}
