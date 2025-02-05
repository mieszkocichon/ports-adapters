package org.example.car;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.common.BaseEntity;
import org.hibernate.envers.Audited;

import java.time.ZonedDateTime;

@Setter
@Getter
@Audited
@ToString(of = {"carId"})
@Entity
@Table(name = "car")
public class CarEntity extends BaseEntity {
    private CarId carId;
    private @Size(min = 3, max = 255) String name;
    private @Size(min = 3, max = 255) String owner;
    private long amount;
    private ZonedDateTime warrantyDate;
}
