package com.valiksk8.dao;

import com.valiksk8.utils.ClassData;
import com.valiksk8.utils.DaoTool;
import com.valiksk8.utils.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T> implements Dao<T> {

    private Class<?> clazz;
    final Connection connection;

    public AbstractDao(Connection connection) {
        this.connection = connection;
        this.clazz = ClassData.getClassFromGeneric(this);
    }

    @Override
    public List<T> findAll() {
        String query = QueryBuilder.getSelectAllQuery(clazz);
        List<T> result = new ArrayList<>();
        Statement statement;
        ResultSet resultSet;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                result.add(getObjectFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public T findById(Long id) {
        String query = QueryBuilder.getSelectByParamQuery(clazz, "ID");

        return simpleConnectAndGetObjectByParam(query, id);
    }

    @Override
    public void add(T entity) {
        String query = QueryBuilder.getInsertQuery(clazz);
        try {
            createPrepareStatement(query, entity)
                    .executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateById(Long id, T entity) {
        String query = QueryBuilder.getUpdateByIdQuery(clazz, id);
        try {
            PreparedStatement statement = createPrepareStatement(query, entity);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long id) {
        String query = QueryBuilder.getDeleteByParamQuery(clazz, "ID");

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected T simpleConnectAndGetObjectByParam(String query, Object param) {
        PreparedStatement statement;
        ResultSet resultSet;
        T resultObject = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setObject(1, param);
            resultSet = statement.executeQuery();
            resultObject = resultSet.next() ? getObjectFromResultSet(resultSet) : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultObject;
    }

    protected T getObjectFromResultSet(ResultSet resultSet) throws SQLException {
        return DaoTool.getObjectFromResultSet(resultSet, clazz);
    }

    protected PreparedStatement createPrepareStatement(String query, T entity) throws SQLException {
        return DaoTool.createPrepareStatement(connection, clazz, query, entity);
    }
}