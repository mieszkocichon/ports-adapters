package org.example.user;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.common.BaseEntity;
import org.hibernate.envers.Audited;

@Setter
@Getter
@Audited
@Entity(name = "user_role")
@AllArgsConstructor
public class UserRole extends BaseEntity {
    private ERole name;
}
