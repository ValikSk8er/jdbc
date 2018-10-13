package com.valiksk8.dao;

import com.valiksk8.model.Product;

import java.sql.Connection;

public class ProductDaoImpl extends AbstractDao<Product> implements ProductDao {
    public ProductDaoImpl(Connection connection) {
        super(connection);
    }

    public Product findByName(String name) {
        String query = "SELECT * FROM Products WHERE NAME = ?";
        return simpleConnectAndGetObjectByParam(query, name);
    }
}
