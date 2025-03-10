package org.example.product;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Builder
@Getter
@ToString(of = {"uuid", "productId", "version", "name", "imageUrl"})
@EqualsAndHashCode(of = "productId")
public class ProductResponse {
    private UUID uuid;
    private UUID productId;
    private int version;
    private String name;
    private String imageUrl;
}
