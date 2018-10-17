package com.valiksk8.dao;

import com.valiksk8.model.Product;
import com.valiksk8.utils.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl extends AbstractDao<Product> implements ProductDao {
    public ProductDaoImpl(Connection connection) {
        super(connection);
    }

    public Product findByName(String name) {
        String query = "SELECT * FROM Products WHERE NAME = ?";
        return simpleConnectAndGetObjectByParam(query, name);
    }

    @Override
    public List<Product> findAllWithCategoryId(Long id) {
        String query = QueryBuilder.getSelectByParamQuery(Product.class, "FK_CATEGORIES");
        List<Product> result = new ArrayList<>();
        PreparedStatement statement;
        ResultSet resultSet;

        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(getObjectFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
