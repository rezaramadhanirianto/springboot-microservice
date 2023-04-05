package com.rezaramadhanirianto.productservice.repository;

import com.rezaramadhanirianto.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
