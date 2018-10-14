package com.valiksk8.controller;

import com.valiksk8.dao.CategoryDao;
import com.valiksk8.model.Category;
import com.valiksk8.web.Request;
import com.valiksk8.web.ViewModel;

import java.sql.SQLException;
import java.util.List;

public class GetAdminAddCategoryContorller implements Controller {

    public GetAdminAddCategoryContorller(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    CategoryDao categoryDao;

    @Override
    public ViewModel process(Request request) throws SQLException {
        ViewModel vm = ViewModel.of("adminCategories");
        String categoryName = request.getParamByName("categoryName");

        Category category = categoryDao.findByName(categoryName);

        if (category != null) {
            vm.addAttribute("msg_add", true);
        } else {
            category = new Category(categoryName);
            categoryDao.add(category);
        }
        List<Category> categories = categoryDao.findAll();
        vm.addAttribute("categories", categories);
        return vm;
    }
}
