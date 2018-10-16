package com.valiksk8.service;

import com.valiksk8.dao.CategoryDao;
import com.valiksk8.model.Category;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public Category findByName(String name) {
        return categoryDao.findByName(name);
    }

    @Override
    public void addByName(String name) {
        Category category = new Category(name);
        add(category);
    }

    @Override
    public Category findById(Long id) {
        return categoryDao.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public void add(Category category) {
        categoryDao.add(category);
    }

    @Override
    public void deleteByName(String categoryName) {
        categoryDao.deleteByName(categoryName);
    }

    @Override
    public Category findWithProductById(Long id) {
        return categoryDao.findWithProductById(id);
    }

    @Override
    public void updateById(Long id, Category category) {
        categoryDao.updateById(id, category);
    }

    @Override
    public void deleteById(Long id) {
        categoryDao.deleteById(id);
    }
}
