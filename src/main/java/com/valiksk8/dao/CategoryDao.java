package com.valiksk8.dao;

import com.valiksk8.model.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao {

    List<Category> findAll();
}
