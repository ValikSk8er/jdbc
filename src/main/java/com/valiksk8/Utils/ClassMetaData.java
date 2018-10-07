package com.valiksk8.Utils;

import com.valiksk8.metadata.ColumnName;
import com.valiksk8.metadata.TableName;

import java.lang.reflect.Field;

public class ClassMetaData {

    public static String getTableNameFromClass(Class<?> clazz) {
        return clazz.isAnnotationPresent(TableName.class)
                ? clazz.getAnnotation(TableName.class).value()
                : null;
    }

    public static String getColumnNameFromField(Field field) {
        return field.isAnnotationPresent(ColumnName.class)
                ? field.getAnnotation(ColumnName.class).value()
                : null;
    }
}
