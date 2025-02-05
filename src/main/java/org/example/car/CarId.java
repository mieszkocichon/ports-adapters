package org.example.car;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString(of = "carId")
@EqualsAndHashCode(of = "carId")
@Embeddable
public class CarId {
    private UUID carId;
}
