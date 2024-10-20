package org.example.car;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Setter
@Getter
public class CarUpdateRequest {
    private @NotNull UUID uuid;
    private @NotNull UUID carId;
    private int version;
    private @Size(min = 3, max = 255) String name;
    private @Size(min = 3, max = 255) String owner;
    private @NotNull Long amount;
    private @NotNull ZonedDateTime warrantyDate;

    void updateCar(CarEntity car) {
        car.setUuid(uuid);
        CarId carId = new CarId();
        carId.setCarId(this.carId);
        car.setCarId(carId);
        car.setVersion(this.version);
        car.setName(name);
        car.setOwner(owner);
        car.setAmount(this.amount);
        car.setWarrantyDate(this.warrantyDate);
    }
}
