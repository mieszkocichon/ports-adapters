package org.example.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CreateCarRequest {
    private Long id;
    private String name;
    private String owner;
    private BigDecimal amount;
}
