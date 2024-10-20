package org.example.car;

import org.example.adapters.Adapter;
import org.springframework.stereotype.Component;

@Component
public class CarToCarEntityAdapter implements Adapter<Car, CarEntity> {
    @Override
    public CarEntity map(Car in) {
        CarEntity carEntity = new CarEntity();
        carEntity.setId(in.getId());
        carEntity.setUuid(in.getUuid());
        carEntity.setVersion(in.getVersion());
        carEntity.setName(in.getName());
        carEntity.setAmount(in.getAmount());
        carEntity.setWarrantyDate(in.getWarrantyDate());
        return carEntity;
    }
}
