package org.example.domain.services;

import lombok.RequiredArgsConstructor;
import org.example.adapters.repository.InMemoryEventStore;
import org.example.domain.controllers.CarEntityToCarResponseMapper;
import org.example.car.Car;
import org.example.domain.model.CarResponse;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CarQueryServiceImpl {

    private final InMemoryEventStore eventStore;
    private final CarEntityToCarResponseMapper carEntityToCarResponseMapper;

    public CarResponse getCar(Long id) {
        Car car = new Car(eventStore.getEventsForAggregate(id));
        CarResponse map = carEntityToCarResponseMapper.map(car);
        return map;
    }
}