package org.example.product;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductRecreateService productRecreateService;

    @PostMapping("create")
    public ProductResponse createCar(@RequestBody ProductCreateRequest request) {
        return productService.createProduct(request);
    }

    @GetMapping
    public List<ProductEntity> getAll() {
        return productService.readProducts();
    }

    @GetMapping("{productId}")
    public ProductResponse getCar(@PathVariable UUID productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("{productId}/recreate")
    public ProductResponse getProductByDate(@PathVariable Long productId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date timestamp) {
        return productRecreateService.getProductAtTimestamp(productId, timestamp);
    }
}
