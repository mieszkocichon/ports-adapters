package org.example.car;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.example.common.BaseEntity;
import org.hibernate.envers.Audited;

import java.time.ZonedDateTime;

@Setter
@Getter
@Audited
@Entity
@Table(name = "car")
public class CarEntity extends BaseEntity {

    private @Size(min = 3, max = 255) String name;
    private @Size(min = 3, max = 255) String owner;
    private @NotNull long amount;
    private ZonedDateTime warrantyDate;
}
