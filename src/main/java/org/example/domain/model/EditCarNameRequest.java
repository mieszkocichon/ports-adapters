package org.example.domain.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EditCarNameRequest {
    private Long id;
    private String owner;
}
