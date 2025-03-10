package org.example.car;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Setter
@Getter
public class CarCreateRequest {
    private @NotBlank @Size(min = 3, max = 255) String name;
    private @NotBlank @Size(min = 3, max = 255) String owner;
    private long amount;
    private @NotNull ZonedDateTime warrantyDate;
}
