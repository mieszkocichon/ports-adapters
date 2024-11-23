package org.example.car.rest;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateCarRequest {
    private @Size(min = 3, max = 255) String name;
    private @Size(min = 3, max = 255) String owner;
    private long amount;
}
