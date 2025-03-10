package org.example.product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ProductRecreateService {

    @PersistenceContext
    private EntityManager entityManager;

    private final ProductEntityToProductResponseAdapter productEntityToProductResponseAdapter;

    @Transactional
    public ProductResponse getProductAtTimestamp(Long productId, Date timestamp) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        Number revision = auditReader.getRevisionNumberForDate(timestamp);

        ProductEntity product = auditReader.find(ProductEntity.class, productId, revision);
        return productEntityToProductResponseAdapter.map(product);
    }
}
