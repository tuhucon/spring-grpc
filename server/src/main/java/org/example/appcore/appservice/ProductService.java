package org.example.appcore.appservice;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.example.appcore.domain.Product;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {

    public Product getProduct(String id) {
        return new Product(id, "name " + id, "description " + id);
    }

    public Product addProduct(String name, String description) {
        return new Product(UUID.randomUUID().toString(), name, description);
    }

}
