package com.valiksk8.service;

import com.valiksk8.model.Product;

import java.util.List;

public interface ProductService extends Service<Product> {
    Product findByName(String name);
    List<Product> findAllWithCategoryId(Long id);
}
