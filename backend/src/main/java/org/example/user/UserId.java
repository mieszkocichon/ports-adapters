package org.example.user;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Setter
@Getter
@ToString(of = "userId")
@EqualsAndHashCode(of = "userId")
@Embeddable
public class UserId {
    private UUID userId;
}
