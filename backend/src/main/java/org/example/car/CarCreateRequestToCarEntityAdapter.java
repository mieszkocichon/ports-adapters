package org.example.car;

import org.example.adapters.Adapter;
import org.example.common.Factory;

import java.util.UUID;

@Factory
public class CarCreateRequestToCarEntityAdapter implements Adapter<CarCreateRequest, CarEntity> {
    @Override
    public CarEntity map(CarCreateRequest in) {
        CarEntity carEntity = new CarEntity();
        carEntity.setUuid(UUID.randomUUID());
        CarId carId = new CarId();
        carId.setCarId(UUID.randomUUID());
        carEntity.setCarId(carId);

        carEntity.setName(in.getName());
        carEntity.setOwner(in.getOwner());
        carEntity.setAmount(in.getAmount());
        carEntity.setWarrantyDate(in.getWarrantyDate());
        return carEntity;
    }
}
