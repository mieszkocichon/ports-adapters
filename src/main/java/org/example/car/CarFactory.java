package org.example.car;

import org.example.car.rest.CreateCarRequest;
import org.example.common.Factory;

import java.time.ZonedDateTime;
import java.util.UUID;

@Factory
public class CarFactory {
    public Car create(CreateCarRequest createRequest) {
        Car car = new Car();
        car.setUuid(UUID.randomUUID());
        car.setVersion(1);
        car.setName(createRequest.getName());
        car.setOwner(createRequest.getOwner());
        car.setAmount(createRequest.getAmount());
        car.setWarrantyDate(ZonedDateTime.now().plusYears(5));
        return car;
    }
}
