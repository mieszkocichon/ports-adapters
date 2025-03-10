package org.example.product;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Setter
@Getter
@Embeddable
@ToString(of = "productId")
@EqualsAndHashCode(of = "productId")
public class ProductId {
    private UUID productId;
}
