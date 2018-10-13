package com.valiksk8.dao;

import com.valiksk8.model.Product;

public interface ProductDao extends Dao<Product>{

    Product findByName(String name);
}
