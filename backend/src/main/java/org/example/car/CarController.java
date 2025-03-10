package org.example.car;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public CarResponse createCar(@Valid @RequestBody CarCreateRequest request) {
        return carService.createCar(request);
    }

    @PostMapping("update")
    public void updateCar(@Valid @RequestBody CarUpdateRequest request) {
        carService.updateCar(request);
    }

    @GetMapping("{id}")
    public CarResponse getCar(@Valid @PathVariable UUID id) {
        return carService.getCarById(id);
    }

    @GetMapping("{carId}/recreate")
    public CarResponse getCarByDate(@Valid @PathVariable Long carId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date timestamp) {
        return carRecreateService.getCarAtTimestamp(carId, timestamp);
    }
}