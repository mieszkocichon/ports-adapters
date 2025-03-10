package org.example.product;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductEntityToProductResponseAdapter productEntityToProductResponseAdapter;
    private final ProductCreateRequestToProductEntityAdapter productCreateRequestToProductEntityAdapter;

    public List<ProductEntity> readProducts () {
        return productRepository.findAll();
    }

    @Transactional
    public ProductResponse createProduct(ProductCreateRequest request) {
        ProductEntity product = productRepository.save(productCreateRequestToProductEntityAdapter.map(request));
        return productEntityToProductResponseAdapter.map(product);
    }

    @Transactional
    public ProductResponse getProductById(UUID id) {
        return productEntityToProductResponseAdapter.map(productRepository
                .findByProductId_ProductId(id)
                .orElseThrow(() -> new ProductNotFoundException(id)));
    }
}
