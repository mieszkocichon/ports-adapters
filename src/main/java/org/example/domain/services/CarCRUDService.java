package org.example.domain.services;

import lombok.RequiredArgsConstructor;
import org.example.domain.components.CarToCarEntityFactory;
import org.example.domain.controllers.CarEntityToCarResponseMapper;
import org.example.car.Car;
import org.example.car.CarEntity;
import org.example.domain.model.CarResponse;
import org.example.domain.repositories.CarRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class CarCRUDService {

    private final CarEntityToCarResponseMapper carEntityToCarResponseMapper;
    private final CarToCarEntityFactory carToCarEntityFactory;
    private final CarRepository carRepository;

    public CarResponse create(Car newCar) {
        CarEntity carEntity = carToCarEntityFactory.create(newCar);
        CarEntity car = carRepository.save(carEntity);
        return carEntityToCarResponseMapper.map(car);
    }

    public CarResponse update(Car newCar) {
        Optional<CarEntity> existingCarOptional = carRepository.findById(newCar.getId());

        if (existingCarOptional.isPresent()) {
            CarEntity existingUser = existingCarOptional.get();
            existingUser.setName(newCar.getName());
            CarEntity save = carRepository.save(existingUser);

            return carEntityToCarResponseMapper.map(save);
        } else {
            throw new RuntimeException("User not found with id: " + newCar.getId());
        }
    }
}
