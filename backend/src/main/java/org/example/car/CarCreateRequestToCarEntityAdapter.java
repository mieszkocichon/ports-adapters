package org.example.car;

import org.example.adapters.Adapter;
import org.example.car.rest.CreateCarRequest;
import org.example.common.Factory;

import java.util.UUID;

@Factory
public class CarCreateRequestToCarEntityAdapter implements Adapter<CreateCarRequest, CarEntity> {
    @Override
    public CarEntity map(CreateCarRequest in) {
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
