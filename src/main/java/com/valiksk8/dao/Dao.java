package com.valiksk8.dao;

import java.util.List;

public interface Dao<T> {

    T findById(Long id);
    List<T> findAll();
    void add(T t);
    void update(T t);
    void delete(T t);

}
