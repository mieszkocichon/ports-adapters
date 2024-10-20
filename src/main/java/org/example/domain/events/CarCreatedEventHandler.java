package org.example.domain.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CarCreatedEventHandler {

    @EventListener
    public void onCarCreated(CarCreateEvent event) {
        System.out.println("Car created with ID: " + event.getCarId());
        // Additional logic (e.g., sending notifications or updating other systems)
    }
}