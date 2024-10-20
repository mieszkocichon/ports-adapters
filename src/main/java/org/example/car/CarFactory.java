package org.example.car;

import org.example.common.Factory;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Factory
public class CarFactory {
    public static Car create(String name, String owner, BigDecimal amount, ZonedDateTime warrantyDate) {
        Car car = new Car(new ArrayList<>());
        car.setUuid(UUID.randomUUID());
        car.setVersion(1);
        car.setName(name);
        car.setOwner(owner);
        car.setAmount(amount);
        car.setWarrantyDate(warrantyDate);
        return car;
    }
}
