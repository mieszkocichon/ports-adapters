package org.example.car;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long in) {
        super("Car not found with id: " + in);
    }
}
