package org.example.car;

import lombok.Getter;
import lombok.Setter;
import org.example.common.BaseEntity;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Setter
@Getter
@Audited
@Entity
@Table(name = "car")
public class CarEntity extends BaseEntity {

    private @Size(min = 3, max = 255) String name;
    private @Size(min = 3, max = 255) String owner;
    private @NotNull BigDecimal amount;
    private ZonedDateTime warrantyDate;
}
