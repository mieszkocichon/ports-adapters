package org.example.product;

import org.example.car.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    Optional<ProductEntity> findByUuid(UUID uuid);
    Optional<ProductEntity> findByProductId_ProductId(UUID productId);
}
