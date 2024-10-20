package org.example.car;

import lombok.RequiredArgsConstructor;
import org.example.car.rest.CreateCarRequest;
import org.example.car.rest.CarResponse;
import org.example.domain.controllers.CarEntityToCarResponseAdapter;
import org.example.domain.repositories.CarRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CarService {
    private final CarRepository carRepository;
    private final CarFactory carFactory;
    private final CarToCarEntityAdapter carToCarEntityAdapter;
    private final CarEntityToCarResponseAdapter carEntityToCarResponseAdapter;

    public CarResponse create(CreateCarRequest request) {
        Car car = carFactory.create(request);
        CarEntity carEntity = carRepository
                .save(carToCarEntityAdapter
                        .map(car));
        return carEntityToCarResponseAdapter.map(carEntity);
    }
}
