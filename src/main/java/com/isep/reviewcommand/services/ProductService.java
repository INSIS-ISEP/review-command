package com.isep.reviewcommand.services;

import java.util.Optional;

import com.isep.reviewcommand.model.Product;


public interface ProductService {

    Optional<Product> findBySku(final String sku);



}
