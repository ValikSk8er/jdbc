package com.valiksk8.utils;

import java.lang.reflect.Field;

public class QueryBuilder {
    private static final String separator = ", ";

    public static String getSelectAllQuery(Class<?> clazz) {
        String tableName = ClassMetaData.getTableNameFromClass(clazz);
        String fieldsNames = getFieldsNames(clazz.getDeclaredFields());
        return String.format("SELECT %s FROM %s", fieldsNames, tableName);
    }

    public static String getSelectByQuery(Class<?> clazz, String byParam) {
        String tableName = ClassMetaData.getTableNameFromClass(clazz);
        String fieldsNames = getFieldsNames(clazz.getDeclaredFields());
        return String.format("SELECT %s FROM %s WHERE %s = ?", fieldsNames, tableName, byParam);
    }

    public static String getDeleteByQuery(Class<?> clazz, String byParam) {
        String tableName = ClassMetaData.getTableNameFromClass(clazz);
        return String.format("DELETE FROM %s WHERE %s = ?;", tableName, byParam);
    }

    public static String getInsertQuery(Class<?> clazz) {
        String tableName = ClassMetaData.getTableNameFromClass(clazz);
        String fieldsNames = getFieldsNamesWithoutId(clazz.getDeclaredFields());
        int fieldsNumber = fieldsNames.split(separator).length;
        String queryValueSign = "?";
        String values = getValuesWithSigns(fieldsNumber, queryValueSign);

        return String.format("INSERT INTO %s (%s) VALUES (%s);", tableName, fieldsNames, values);
    }

    public static String getUpdateByIdQuery(Class<?> clazz, Long id) {
        StringBuilder values = new StringBuilder();
        String tableName = ClassMetaData.getTableNameFromClass(clazz);
        String queryValueSign = " = ?";

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            values.append(field.getName())
                    .append(queryValueSign)
                    .append(separator);
        }

        values.substring(0, values.length() - separator.length());
        return String.format("UPDATE %s SET %s WHERE ID = %d;", tableName, values, id);
    }

    private static String getFieldsNames(Field[] fields) {
        StringBuilder fieldsNames = new StringBuilder();
        for (Field field : fields) {
            String columnName = ClassMetaData.getColumnNameFromField(field);
            if (columnName != null) {
                fieldsNames.append(columnName)
                        .append(separator);
            }
        }
        return fieldsNames.substring(0, fieldsNames.length() - separator.length());
    }

    private static String getFieldsNamesWithoutId(Field[] fields) {
        StringBuilder fieldsNames = new StringBuilder();

        for (Field field : fields) {
            String columnName = ClassMetaData.getColumnNameFromField(field);
            if (columnName != null && !columnName.equals("ID")) {
                fieldsNames.append(columnName)
                        .append(separator);
            }
        }

        return fieldsNames.substring(0, fieldsNames.length() - separator.length());
    }

    private static String getValuesWithSigns(int signCount, String valueSign) {
        StringBuilder values = new StringBuilder();
        for (int i = 0; i < signCount; i++) {
            values.append(valueSign)
                    .append(separator);
        }
        return values.substring(0, values.length() - separator.length());
    }
}
