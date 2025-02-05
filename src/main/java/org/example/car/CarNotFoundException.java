package org.example.car;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Car with given id not found")
public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(Long id) {
        super("Car not found with id: " + id);
    }

    public CarNotFoundException(UUID id) {
        super("Car not found with uuid: " + id);
    }
}
