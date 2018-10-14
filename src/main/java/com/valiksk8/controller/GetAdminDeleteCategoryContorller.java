package com.valiksk8.controller;

import com.valiksk8.dao.CategoryDao;
import com.valiksk8.dao.CategoryDaoImpl;
import com.valiksk8.model.Category;
import com.valiksk8.web.Request;
import com.valiksk8.web.ViewModel;

import java.sql.SQLException;
import java.util.List;

public class GetAdminDeleteCategoryContorller implements Controller {

    CategoryDao categoryDao;

    public GetAdminDeleteCategoryContorller(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public ViewModel process(Request request) throws SQLException {
        ViewModel vm;
        String categoryName = request.getParamByName("categoryName");
        String categoryId = request.getParamByName("categoryId");

        if (categoryName == null) {
            if (categoryId == null) {
                return null;
            }
            vm = processDeleteCategoryById(categoryId);
        } else {
            vm = processDeleteCategoryByName(categoryName);
        }

        List<Category> categories = categoryDao.findAll();
        vm.addAttribute("categories", categories);
        return vm;
    }

    private ViewModel processDeleteCategoryById(String categoryId) {
        ViewModel vm = ViewModel.of("adminCategories");
        Long id = Long.parseLong(categoryId);
        Category category = categoryDao.findById(id);
        if (category == null) {
            vm.addAttribute("msg_delete_id", true);
        } else {
            categoryDao.deleteById(id);
        }
        return vm;
    }

    private ViewModel processDeleteCategoryByName(String categoryName) {
        ViewModel vm = ViewModel.of("adminCategories");
        Category category = categoryDao.findByName(categoryName);

        if (category == null) {
            vm.addAttribute("msg_delete_name", true);
        } else {
            categoryDao.deleteByName(categoryName);
        }
        return vm;
    }
}
