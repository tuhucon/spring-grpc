package org.example.presentation.http;

import lombok.RequiredArgsConstructor;
import org.example.appcore.appservice.ProductService;
import org.example.appcore.domain.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable String id) {
        return productService.getProduct(id);
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product.getName(), product.getDescription());
    }
}
