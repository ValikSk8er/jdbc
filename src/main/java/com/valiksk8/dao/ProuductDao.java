package com.valiksk8.dao;

import com.valiksk8.model.Product;

public interface ProuductDao {

    Product findByName(String name);

    void add(Product product);
}
