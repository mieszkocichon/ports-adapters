package org.example.car;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CarRecreateService {

    @PersistenceContext
    private EntityManager entityManager;

    public Car getCarAtTimestamp(Long id, Date date) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        Number revision = auditReader.getRevisionNumberForDate(date);

        return auditReader.find(Car.class, id, revision);
    }
}
