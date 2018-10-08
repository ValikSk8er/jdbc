package com.valiksk8.dao;

import com.valiksk8.utils.ClassData;
import com.valiksk8.utils.QueryBuilder;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T> implements Dao<T> {

    private Class<?> clazz;
    final Connection connection;

    public AbstractDao(Connection connection) {
        this.connection = connection;
        clazz = ClassData.getClassFromGeneric(this);
    }

    protected abstract T getObjectFromResultSet(ResultSet resultSet) throws SQLException;

    @Override
    public List<T> findAll() {
        String query = QueryBuilder.getSelectAllQuery(this.clazz);
        List<T> result = new ArrayList<>();
        Statement statement;
        ResultSet resultSet;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            //TO DO: add implementation resultSetMetaData
            ResultSetMetaData metaData = resultSet.getMetaData();

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
        String query = QueryBuilder.getSelectByParamQuery(this.clazz, "ID");
        PreparedStatement statement;
        ResultSet resultSet;
        T entity = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            entity = resultSet.next() ? getObjectFromResultSet(resultSet) : null;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
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
        String query = QueryBuilder.getDeleteByParamQuery(this.clazz, "ID");

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private PreparedStatement createPrepareStatement(String query, T entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        Field[] fields = clazz.getDeclaredFields();
        int paramIndex = 1;
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(entity);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            try {
                if(value != null){
                    statement.setObject(paramIndex++, value);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return statement;
    }
}
