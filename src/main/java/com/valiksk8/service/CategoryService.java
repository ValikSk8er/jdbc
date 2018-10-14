package com.valiksk8.service;

import com.valiksk8.model.Category;

public interface CategoryService {
    Category findByName(String name);
    void addCategoryByName(String name);
    void addCategory(Category category);
}
