package org.example.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.common.BaseEntity;
import org.hibernate.envers.Audited;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Audited
@Table(name = "users",
    uniqueConstraints = {
            @UniqueConstraint(columnNames = "username"),
            @UniqueConstraint(columnNames = "email")
    })
@Entity
@ToString(of = "userId")
public class User extends BaseEntity {

    private @Embedded UserId userId;
    private @NotBlank @Size(min = 3, max = 255) String username;
    private @NotBlank @Size(min = 3, max = 255) String email;
    private @NotBlank @Size(min = 3, max = 255) String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<UserRole> roles = new HashSet<>();

}
