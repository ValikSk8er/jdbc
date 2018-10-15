package com.valiksk8.service;

import java.util.List;

public interface Service<T> {
        T findById(Long id);
        List<T> findAll();
        void add(T t);
        void updateById(Long id, T entity);
        void deleteById(Long id);
}
