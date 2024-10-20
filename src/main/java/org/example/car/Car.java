package org.example.car;

import lombok.Getter;
import lombok.Setter;
import org.example.domain.events.CarCreateEvent;
import org.example.domain.events.CarEditNameEvent;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class Car {
    private Long id;
    private @NotNull UUID uuid;
    private int version;
    private @Size(min = 3, max = 255) String name;
    private @Size(min = 3, max = 255) String owner;
    private BigDecimal amount;
    private ZonedDateTime warrantyDate;

    @Getter
    private final List<Object> changes = new ArrayList<>();

    public Car(List<Object> events) {
        events.forEach(this::apply);
    }

    public static Car create(Long id, String name, String owner, BigDecimal amount) {
        Car car = new Car(new ArrayList<>());
        CarCreateEvent event = new CarCreateEvent(id, name, owner);
        car.apply(event);  // Apply the event (modify the state)
        car.changes.add(event);  // Add the event to the list of changes (track it)
        car.name = name;
        car.owner = owner;
        car.amount = amount;
        return car;
    }

    public static Car editName(Long id, String name) {
        Car car = new Car(new ArrayList<>());
        CarEditNameEvent event = new CarEditNameEvent(id, name);
        car.apply(event);
        car.changes.add(event);
        car.name = name;
        return car;
    }

    public void apply(Object event) {
        if (event instanceof CarCreateEvent) {
            this.id = ((CarCreateEvent) event).getCarId();
            this.name = ((CarCreateEvent) event).getName();
            this.owner = ((CarCreateEvent) event).getOwner();
        }
        if (event instanceof CarEditNameEvent) {
            this.id = ((CarEditNameEvent) event).getCarId();
            this.name = ((CarEditNameEvent) event).getName();
        }
        // Handle other event types here
    }

    public void markChangesAsCommitted() {
        changes.clear();
    }
}