package org.example.car;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CarRepository extends JpaRepository<CarEntity, Long> {
    Optional<CarEntity> findByUuid(UUID uuid);
    Optional<CarEntity> findByCarId_CarId(UUID carId);
}
