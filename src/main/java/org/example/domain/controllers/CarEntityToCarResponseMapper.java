package org.example.domain.controllers;

import org.example.car.CarEntity;
import org.example.domain.model.CarResponse;
import org.springframework.stereotype.Controller;

@Controller
public class CarEntityToCarResponseMapper {
    public CarResponse map(CarEntity car) {
        return CarResponse.builder()
                .id(car.getId())
                .name(car.getName())
                .owner(car.getOwner())
                .amount(car.getAmount())
                .build();
    }
}
