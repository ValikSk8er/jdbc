package com.valiksk8.dao;

import com.valiksk8.Utils.ClassData;
import com.valiksk8.metadata.ColumnName;
import com.valiksk8.metadata.TableName;

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
    private String tableName;
    final Connection connection;

    public AbstractDao(Connection connection) {
        this.connection = connection;

        String type = ClassData.getParameterizedTypes(this).getTypeName();
        clazz = ClassData.getClass(type);
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
        String query = String.format("SELECT * FROM %s WHERE ID = ?", tableName.toUpperCase());
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

        StringBuilder fieldsNames = new StringBuilder();
        StringBuilder values = new StringBuilder();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            String columnName = field.isAnnotationPresent(ColumnName.class)
                    ? field.getAnnotation(ColumnName.class).value()
                    : null;
            if (columnName != null) {
                fieldsNames.append(columnName)
                        .append(", ");
                values.append("?, ");
            }
        }

        String query = String.format("INSERT INTO %s (%s) VALUES (%s);", tableName, fieldsNames, values);

        try {
            createPrepareStatement(query, entity);
//            .executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateById(Long id, T entity) {
        StringBuilder values = new StringBuilder();

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            values.append(field.getName())
                    .append(" = ?, ");
        }

        String query = String.format("UPDATE %s SET %s WHERE ID = %d;", tableName, values.substring(0, values.length() - 2), id);

        try {
            PreparedStatement statement = createPrepareStatement(query, entity);
            statement.executeUpdate();

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
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
}
