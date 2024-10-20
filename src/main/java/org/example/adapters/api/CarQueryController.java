package org.example.adapters.api;

import lombok.RequiredArgsConstructor;
import org.example.car.rest.CarResponse;
import org.example.domain.services.CarQueryServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("car-query")
public class CarQueryController {
    private final CarQueryServiceImpl carQueryService;

    @GetMapping("{id}")
    public ResponseEntity<CarResponse> getCar(@PathVariable Long id) {
        return ResponseEntity.ok(carQueryService.getCar(id));
    }
}