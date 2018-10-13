package com.valiksk8.dao;

import com.valiksk8.utils.ClassData;
import com.valiksk8.utils.ClassMetaData;
import com.valiksk8.utils.QueryBuilder;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        String query = QueryBuilder.getDeleteByParamQuery(clazz, "ID");

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
                if (value != null) {
                    statement.setObject(paramIndex++, value);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return statement;
    }

    protected T getObjectFromResultSet(ResultSet resultSet) throws SQLException {
        T resultObject = null;

        if (clazz == null) {
            return null;
        }
        ResultSetMetaData metaData = resultSet.getMetaData();
        Set<String> columNames = new HashSet<>();

        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            columNames.add(metaData.getColumnName(i));
        }
        try {
            resultObject = (T) clazz.newInstance();

            for (Field field : clazz.getDeclaredFields()) {
                String currentColumName = ClassMetaData.getColumnNameFromField(field);
                if (columNames.contains(currentColumName)) {
                    field.setAccessible(true);
                    Object value = resultSet.getObject(currentColumName);
                    Class<?> type = field.getType();
                    if (type.equals(double.class)) {
                        value = Double.parseDouble(value.toString());
                    }
                    field.set(resultObject, value);
                }
            }
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return resultObject;
    }
}