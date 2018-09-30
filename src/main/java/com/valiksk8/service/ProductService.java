package com.valiksk8.service;

import com.valiksk8.model.Product;

import java.util.List;

public interface ProductService {

    void add(Product product);

    Product findByName(String name);

//    List<Product> findAll();

}
