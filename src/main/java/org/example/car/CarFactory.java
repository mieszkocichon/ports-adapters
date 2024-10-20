package org.example.car;

import org.example.car.rest.CreateCarRequest;
import org.example.common.Factory;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Factory
public class CarFactory {
    public Car create(CreateCarRequest createRequest) {
        Car car = new Car(new ArrayList<>());
        car.setUuid(UUID.randomUUID());
        car.setVersion(1);
        car.setName(createRequest.getName());
        car.setOwner(createRequest.getOwner());
        car.setAmount(createRequest.getAmount());
        car.setWarrantyDate(createRequest.getWarrantyDate());
        return car;
    }
}
