package com.valiksk8.dao;

import com.valiksk8.model.AbstractModel;
import com.valiksk8.model.TableName;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T extends AbstractModel> implements Dao<T> {

    private Class<?> clazz;
    private String tableName;
    final Connection connection;

    public AbstractDao(Connection connection) {
        this.connection = connection;

        String type = getParameterizedTypes(this)[0].getTypeName();
        clazz = getClass(type);
        tableName = clazz.isAnnotationPresent(TableName.class)
                ? clazz.getAnnotation(TableName.class).value()
                : null;
    }

    protected abstract T getObjectFromResultSet(ResultSet resultSet) throws SQLException;

    @Override
    public List<T> findAll() {
        String query = String.format("SELECT * FROM %s", tableName.toUpperCase());
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
        String query = String.format("SELECT * FROM %s WHERE ID = ?", tableName.toUpperCase());
        PreparedStatement statement;
        ResultSet resultSet;
        T entity = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            entity = resultSet.next() ? (T) getObjectFromResultSet(resultSet) : null;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public void add(T entity) {

        StringBuilder fieldsNames = new StringBuilder();
        StringBuilder values = new StringBuilder();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            fieldsNames.append(field.getName())
                .append(", ");
            values.append("?, ");
        }

        String query = String.format("INSERT INTO %s (%s) VALUES (%s);", tableName, fieldsNames, values);

        try {
            createPrepareStatement(query, entity)
            .executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long id) {

        String query = String.format("DELETE FROM %s WHERE ID = ?;", tableName);

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateById(Long id) {
//        UPDATE table_name
//        SET column1 = value1, column2 = value2, ...
//        WHERE condition;
    }

    private PreparedStatement createPrepareStatement(String query, T entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        Field[] fields = clazz.getDeclaredFields();

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
                statement.setObject(i + 1, value);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return statement;
    }

    private Class<?> getClass(String className) {
        Class<?> someClass = null;
        try {
            someClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return someClass;
    }

    private static Type[] getParameterizedTypes(Object object) {
        Type superclassType = object.getClass().getGenericSuperclass();
        if (!ParameterizedType.class.isAssignableFrom(superclassType.getClass())) {
            return null;
        }
        return ((ParameterizedType) superclassType).getActualTypeArguments();
    }
}
