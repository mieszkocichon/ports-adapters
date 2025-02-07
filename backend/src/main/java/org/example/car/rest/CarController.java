package org.example.car.rest;

import lombok.RequiredArgsConstructor;
import org.example.car.CarRecreateService;
import org.example.car.CarService;
import org.example.car.CarUpdateRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("car")
public class CarController {
    private final CarService carService;
    private final CarRecreateService carRecreateService;

    @PostMapping("create")
    public CarResponse create(@RequestBody CreateCarRequest request) {
        return carService.create(request);
    }

    @PostMapping("update")
    public void update(@RequestBody CarUpdateRequest request) {
        carService.update(request);
    }

    @GetMapping("{id}")
    public CarResponse get(@PathVariable UUID id) {
        return carService.getById(id);
    }

    @GetMapping("{carId}/recreate")
    public CarResponse getCarByDate(@PathVariable Long carId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date timestamp) {
        return carRecreateService.getCarAtTimestamp(carId, timestamp);
    }
}