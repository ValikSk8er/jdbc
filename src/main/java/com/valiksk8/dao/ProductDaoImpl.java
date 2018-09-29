package com.valiksk8.dao;

import com.valiksk8.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDaoImpl extends AbstractDao<Product> implements ProuductDao{
    public ProductDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected Product getObjectFromResultSet(ResultSet resultSet) throws SQLException {
        return getProductFromResultSet(resultSet);
    }

    public void add(Product product) {

        String query = "INSERT INTO PRODUCTS (NAME, PRICE, DESCRIPTION) VALUES (?, ?, ?);";
        PreparedStatement statement;

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Product findByName(String name) {
        String query = "SELECT * FROM Products WHERE NAME = ?";
        PreparedStatement statement;
        ResultSet resultSet;
        Product product = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            product = resultSet.next() ? getObjectFromResultSet(resultSet) : null;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    private Product getProductFromResultSet (ResultSet resultSet) throws SQLException{
        return new Product(
            resultSet.getLong(1),
            resultSet.getString(2),
            resultSet.getDouble(3),
            resultSet.getString(4));
    }

}