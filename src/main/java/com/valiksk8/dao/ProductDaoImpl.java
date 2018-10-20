package com.valiksk8.dao;

import com.valiksk8.model.Category;
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
    public List<Product> findAllByCategoryId(Long id) {
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

    @Override
    public List<Product> findAll() {
        String query = "SELECT P.ID, P.NAME, P.PRICE, P.DESCRIPTION, C.CATEGORY_NAME FROM PRODUCTS P " +
                "JOIN CATEGORIES C ON P.FK_CATEGORIES = C.ID";
        List<Product> result = new ArrayList<>();
        Statement statement;
        ResultSet resultSet;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                result.add(getProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Product getProductFromResultSet(ResultSet resultSet) throws SQLException {
        Category category = new Category(resultSet.getString(5));
        return new Product(
                resultSet.getLong(1),
                resultSet.getString(2),
                resultSet.getDouble(3),
                resultSet.getString(4),
                category
        );
    }
}
