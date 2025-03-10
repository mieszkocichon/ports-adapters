package org.example.user;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.example.common.BaseEntity;
import org.hibernate.envers.Audited;

@Setter
@Getter
@Audited
@Entity
@Table(name = "user_role")
public class UserRole extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private RoleName roleName;
}
