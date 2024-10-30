package org.example.car.rest;

import lombok.RequiredArgsConstructor;
import org.example.car.CarService;
import org.example.domain.model.EditCarNameRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("car")
public class CarCommandController {
    private final CarService carService;

    @PostMapping("create")
    public CarResponse create(@RequestBody CreateCarRequest request) {
        return carService.create(request);
    }

    @PostMapping("update-name")
    public CarResponse updateOwner(@RequestBody EditCarNameRequest request) {
        return carService.updateOwner(request);
    }
}