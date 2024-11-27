package org.example.car;

public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(Long in) {
        super("Car not found with id: " + in);
    }
}
