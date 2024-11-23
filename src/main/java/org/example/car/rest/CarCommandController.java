package org.example.car.rest;

import lombok.RequiredArgsConstructor;
import org.example.car.CarService;
import org.example.domain.model.EditCarNameRequest;
import org.springframework.web.bind.annotation.*;

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
    public void updateOwner(@RequestBody EditCarNameRequest request) {
        carService.updateOwner(request);
    }

    @GetMapping("{id}")
    public CarResponse get(@PathVariable Long id) {
        return carService.getById(id);
    }
}