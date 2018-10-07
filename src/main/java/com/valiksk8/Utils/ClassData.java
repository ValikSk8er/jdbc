package com.valiksk8.Utils;

import com.valiksk8.dao.AbstractDao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ClassData {


    public static Class<?> getClassFromType(String className) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    public static Type getParameterizedTypes(Object object) {
        Type superclassType = object.getClass().getGenericSuperclass();
        if (!ParameterizedType.class.isAssignableFrom(superclassType.getClass())) {
            return null;
        }
        return ((ParameterizedType) superclassType).getActualTypeArguments()[0];
    }


    public static Class<?> getClassFromGeneric(Object object) {
        String type = ClassData.getParameterizedTypes(object).getTypeName();
        return getClassFromType(type);
    }
}
