package org.example.car;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.car.rest.CarResponse;
import org.example.car.rest.CreateCarRequest;
import org.example.domain.controllers.CarEntityToCarResponseAdapter;
import org.example.domain.model.EditCarNameRequest;
import org.example.domain.repositories.CarRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CarService {
    private final CarRepository carRepository;
    private final CarFactory carFactory;
    private final CarToCarEntityAdapter carToCarEntityAdapter;
    private final CarEntityToCarResponseAdapter carEntityToCarResponseAdapter;

    @Transactional
    public CarResponse create(CreateCarRequest request) {
        Car car = carFactory.create(request);
        CarEntity carEntity = carRepository.save(carToCarEntityAdapter.map(car));
        return carEntityToCarResponseAdapter.map(carEntity);
    }

    @Transactional
    public void updateOwner(EditCarNameRequest request) {
         CarEntity car = carRepository
                 .findById(request.getId())
                 .orElseThrow(() -> new UserNotFoundException(request.getId()));
         car.setOwner(request.getOwner());
    }

    @Transactional
    public CarResponse getById(Long id) {
        return carEntityToCarResponseAdapter.map(carRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id)));
    }
}
