package org.example.car;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Setter
@Getter
public class Car {
    private Long id;
    private @NotNull UUID uuid;
    private int version;
    private @Size(min = 3, max = 255) String name;
    private @Size(min = 3, max = 255) String owner;
    private long amount;
    private ZonedDateTime warrantyDate;

    public static Car create(String name, String owner, long amount) {
        Car car = new Car();
        car.name = name;
        car.owner = owner;
        car.amount = amount;
        return car;
    }

    public static Car editName(String name) {
        Car car = new Car();
        car.name = name;
        return car;
    }
}