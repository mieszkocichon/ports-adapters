package org.example.adapters.api;

import lombok.RequiredArgsConstructor;
import org.example.domain.model.*;
import org.example.domain.services.CarCommandServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("car-command")
public class CarCommandController {
    private final CarCommandServiceImpl carCommandService;

    @PostMapping("create")
    public ResponseEntity<CarResponse> create(@RequestBody CreateCarRequest request) {
        return ResponseEntity.ok(carCommandService.create(request));
    }

    @PostMapping("edit-name")
    public ResponseEntity<CarResponse> editName(@RequestBody EditCarNameRequest request) {
        return ResponseEntity.ok(carCommandService.editName(request));
    }
}