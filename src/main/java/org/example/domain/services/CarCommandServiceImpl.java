package org.example.domain.services;

import lombok.RequiredArgsConstructor;
import org.example.adapters.repository.InMemoryEventStore;
import org.example.domain.controllers.CarEntityToCarResponseAdapter;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CarCommandServiceImpl {

    private final InMemoryEventStore eventStore;
    private final CarEntityToCarResponseAdapter carEntityToCarResponseAdapter;
    private final CarCRUDService carCRUDService;

//    public CarResponse create(CreateCarRequest car) {
//        Car newCar = Car.create(car.getId(), car.getName(), car.getOwner(), car.getAmount());
//        newCar.getChanges().forEach(eventStore::saveEvent);
//        newCar.markChangesAsCommitted();
//
//        return carCRUDService.create(newCar);
//    }

//    public CarResponse editName(EditCarNameRequest car) {
//        Car newCar = Car.editName(car.getId(), car.getName());
//        newCar.getChanges().forEach(eventStore::saveEvent);
//        newCar.markChangesAsCommitted();
//
//        return carCRUDService.update(newCar);
//    }
}