package com.valiksk8.dao;

import com.valiksk8.model.Product;

import java.util.List;

public interface ProductDao extends Dao<Product>{

    Product findByName(String name);
    List<Product> findAllWithCategoryId(Long id);
}
