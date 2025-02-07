package org.example.car;

import org.example.adapters.Adapter;
import org.example.car.rest.CarResponse;
import org.springframework.stereotype.Component;

@Component
public class CarEntityToCarResponseAdapter implements Adapter<CarEntity, CarResponse> {
    public CarResponse map(CarEntity car) {
        return CarResponse.builder()
                .uuid(car.getUuid())
                .carId(car.getCarId().getCarId())
                .version(car.getVersion())
                .name(car.getName())
                .owner(car.getOwner())
                .amount(car.getAmount())
                .warrantyDate(car.getWarrantyDate())
                .build();
    }
}
