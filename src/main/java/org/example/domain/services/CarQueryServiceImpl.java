package org.example.domain.services;

import lombok.RequiredArgsConstructor;
import org.example.adapters.repository.InMemoryEventStore;
import org.example.domain.controllers.CarEntityToCarResponseAdapter;
import org.example.car.Car;
import org.example.car.rest.CarResponse;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CarQueryServiceImpl {

    private final InMemoryEventStore eventStore;
    private final CarEntityToCarResponseAdapter carEntityToCarResponseAdapter;

//    public CarResponse getCar(Long id) {
//        Car car = new Car(eventStore.getEventsForAggregate(id));
//        CarResponse map = carEntityToCarResponseAdapter.map(car);
//        return map;
//    }
}