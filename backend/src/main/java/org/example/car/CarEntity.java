package org.example.car;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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
@ToString(of = "carId")
@Entity
@Table(name = "car")
public class CarEntity extends BaseEntity {
    private @Embedded CarId carId;
    private @NotBlank @Size(min = 3, max = 255) String name;
    private @NotBlank @Size(min = 3, max = 255) String owner;
    private long amount;
    private ZonedDateTime warrantyDate;
}
