package org.example.car;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EditCarNameRequest {
    private Long id;
    private String owner;
}
