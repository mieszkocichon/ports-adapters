package org.example.car.rest;

import lombok.RequiredArgsConstructor;
import org.example.car.CarService;
import org.example.domain.model.*;
import org.example.domain.services.CarCommandServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("car")
public class CarCommandController {
    private final CarService carService;
    private final CarCommandServiceImpl carCommandService;

    @PostMapping("create")
    public CarResponse create(@RequestBody CreateCarRequest request) {
        return carService.create(request);
    }

    @PostMapping("edit-name")
    public ResponseEntity<CarResponse> editName(@RequestBody EditCarNameRequest request) {
        return ResponseEntity.ok(carCommandService.editName(request));
    }
}