package org.example.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductCreateRequest {
    private @NotBlank @Size(min = 3, max = 255) String name;
    private @NotBlank @Size(min = 3, max = 255) String owner;
    private @NotBlank @Size(min = 3, max = 255) String imageUrl;
}
