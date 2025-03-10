package org.example.product;

import org.example.adapters.Adapter;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityToProductResponseAdapter implements Adapter<ProductEntity, ProductResponse> {
    public ProductResponse map(ProductEntity product) {
        return ProductResponse.builder()
                .uuid(product.getUuid())
                .productId(product.getProductId().getProductId())
                .version(product.getVersion())
                .name(product.getName())
                .imageUrl(product.getImageUrl())
                .build();
    }
}
