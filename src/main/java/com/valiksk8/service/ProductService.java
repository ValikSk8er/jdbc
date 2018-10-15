package com.valiksk8.service;

import com.valiksk8.model.Product;

public interface ProductService extends Service<Product> {

    Product findByName(String name);
}
