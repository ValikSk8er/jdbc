package com.valiksk8.service;

import com.valiksk8.model.Category;

public interface CategoryService extends Service<Category>{
    Category findByName(String name);
    void addByName(String name);
    void add(Category category);

    void deleteByName(String categoryName);

    boolean checkCategoryExist(String categoryName);

    boolean checkCategoryExist(Long id);

    Category findWithProductById(Long id);
}
