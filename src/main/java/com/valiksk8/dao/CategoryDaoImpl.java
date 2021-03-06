package com.valiksk8.dao;

import com.valiksk8.model.Category;
import com.valiksk8.model.Product;
import com.valiksk8.utils.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl extends AbstractDao<Category> implements CategoryDao {
    public CategoryDaoImpl(Connection connection) {
        super(connection);
    }

    public Category findWithProductById(Long id) {
        String query = "SELECT C.ID, C.CATEGORY_NAME, P.ID, P.NAME, P.PRICE, P.DESCRIPTION " +
                "FROM CATEGORIES C JOIN PRODUCTS P " +
                "ON C.ID = P.FK_CATEGORIES " +
                "WHERE C.ID = ?";
        PreparedStatement statement;
        ResultSet resultSet;
        Category result = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            result = resultSet.next() ? getObjectFromResultSetWithProduct(resultSet) : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private Category getObjectFromResultSetWithProduct(ResultSet resultSet) throws SQLException {
        List<Product> products = new ArrayList<>();
        Category result = new Category(
                resultSet.getLong(1),
                resultSet.getString(2),
                products);
        while (!resultSet.isAfterLast()) {
            Product product = new Product(
                    resultSet.getLong(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    resultSet.getString(6));
            products.add(product);
            resultSet.next();
        }
        return result;
    }

    @Override
    public Category findByName(String name) {
        String query = QueryBuilder.getSelectByParamQuery(Category.class, "CATEGORY_NAME");
        return simpleConnectAndGetObjectByParam(query, name);
    }
}
