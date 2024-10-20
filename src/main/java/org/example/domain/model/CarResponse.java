package org.example.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Builder
@Getter
@ToString(of = {"id", "name", "owner", "amount"})
public class CarResponse {
    private Long id;
    private String name;
    private String owner;
    private BigDecimal amount;
}
