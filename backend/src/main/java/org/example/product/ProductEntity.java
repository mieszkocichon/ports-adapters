package org.example.product;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.common.BaseEntity;
import org.hibernate.envers.Audited;

@Setter
@Getter
@Audited
@Entity
@Table(name = "product")
@ToString(of = "productId")
public class ProductEntity extends BaseEntity {

    private @Embedded ProductId productId;
    private String name;
    private String imageUrl;
}