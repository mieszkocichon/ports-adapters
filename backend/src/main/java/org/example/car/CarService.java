package org.example.car;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.car.rest.CarResponse;
import org.example.car.rest.CreateCarRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CarService {
    private final CarRepository carRepository;
    private final CarCreateRequestToCarEntityAdapter carCreateRequestToCarEntityAdapter;
    private final CarEntityToCarResponseAdapter carEntityToCarResponseAdapter;

    @Transactional
    public CarResponse create(CreateCarRequest request) {
        CarEntity carEntity = carRepository.save(carCreateRequestToCarEntityAdapter.map(request));
        return carEntityToCarResponseAdapter.map(carEntity);
    }

    public void update(CarUpdateRequest request) {
        CarEntity car = carRepository
                .findByUuid(request.getUuid())
                .or(() -> carRepository.findByCarId_CarId(request.getCarId()))
                .orElseThrow(() -> new CarNotFoundException(request.getUuid()));
        request.updateCar(car);
         carRepository.save(car);
    }

    @Transactional
    public CarResponse getById(UUID id) {
        return carEntityToCarResponseAdapter.map(carRepository
                .findByCarId_CarId(id)
                .orElseThrow(() -> new CarNotFoundException(id)));
    }
}
