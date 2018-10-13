package com.valiksk8.dao;

import com.valiksk8.model.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao extends Dao<Category>{

    List<Category> findAll();
}
