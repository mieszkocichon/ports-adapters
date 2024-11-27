package org.example.car.rest;

import lombok.RequiredArgsConstructor;
import org.example.car.CarRecreateService;
import org.example.car.CarService;
import org.example.car.EditCarNameRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

    @PostMapping("update-name")
    public void updateOwner(@RequestBody EditCarNameRequest request) {
        carService.updateOwner(request);
    }

    @GetMapping("{id}")
    public CarResponse get(@PathVariable Long id) {
        return carService.getById(id);
    }

    @GetMapping("{carId}/recreate")
    public CarResponse getCarByDate(@PathVariable Long carId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date timestamp) {
        return carRecreateService.getCarAtTimestamp(carId, timestamp);
    }
}