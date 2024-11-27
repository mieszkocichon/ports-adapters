package org.example.car;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.car.rest.CarResponse;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class CarRecreateService {

    @PersistenceContext
    private EntityManager entityManager;

    private final CarEntityToCarResponseAdapter carEntityToCarResponseAdapter;

    @Transactional
    public CarResponse getCarAtTimestamp(Long id, Date date) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        Number revision = auditReader.getRevisionNumberForDate(date);

        CarEntity car = auditReader.find(CarEntity.class, id, revision);
        return carEntityToCarResponseAdapter.map(car);
    }
}
