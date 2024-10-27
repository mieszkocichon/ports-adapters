package org.example.car.rest;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Setter
@Getter
public class CreateCarRequest {
    private @Size(min = 3, max = 255) String name;
    private @Size(min = 3, max = 255) String owner;
    private BigDecimal amount;
    private ZonedDateTime warrantyDate;
}
