package org.example.domain.components;

import org.example.car.Car;
import org.example.car.CarEntity;
import org.example.common.Factory;

import java.util.UUID;

@Factory
public class CarToCarEntityFactory {
    public CarEntity create(Car newCar) {
        CarEntity carEntity = new CarEntity();
        carEntity.setName(newCar.getName());
        carEntity.setOwner(newCar.getOwner());
        carEntity.setAmount(newCar.getAmount());
        carEntity.setUuid(UUID.randomUUID());

        return carEntity;
    }
}
