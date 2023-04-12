package com.isep.reviewcommand.repositories;

import org.springframework.data.repository.CrudRepository;

import com.isep.reviewcommand.model.Product;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional<Product> findBySku(String sku);
}

