package com.valiksk8.dao;

import com.valiksk8.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProuductDao {

    void add(Product product);

    List<Product> findAll() throws SQLException;

    Product findByName(String name);

}
