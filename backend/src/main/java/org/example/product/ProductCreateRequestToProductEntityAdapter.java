package org.example.product;

import org.example.adapters.Adapter;
import org.example.common.Factory;

import java.util.UUID;

@Factory
public class ProductCreateRequestToProductEntityAdapter implements Adapter<ProductCreateRequest, ProductEntity> {

    @Override
    public ProductEntity map(ProductCreateRequest in) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setUuid(UUID.randomUUID());
        ProductId productId = new ProductId();
        productId.setProductId(UUID.randomUUID());
        productEntity.setProductId(productId);

        productEntity.setName(in.getName());
        productEntity.setImageUrl(in.getImageUrl());
        return productEntity;
    }
}
