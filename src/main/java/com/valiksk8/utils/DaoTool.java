package com.valiksk8.utils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class DaoTool {

    public static <T> T getObjectFromResultSet(ResultSet resultSet, Class<?> clazz) throws SQLException {
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

    public static  <T> PreparedStatement createPrepareStatement(Connection connection, Class<?> clazz, String query, T entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        boolean isUpdateQuery = query.contains("UPDATE");
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
                if (isUpdateQuery || value != null) {
                    statement.setObject(paramIndex++, value);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return statement;
    }
}
