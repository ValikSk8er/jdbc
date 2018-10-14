package com.valiksk8.controller;

import com.valiksk8.dao.CategoryDao;
import com.valiksk8.dao.CategoryDaoImpl;
import com.valiksk8.model.Category;
import com.valiksk8.web.Request;
import com.valiksk8.web.ViewModel;

import java.util.List;

public class GetAdminCategoryController implements Controller{

    CategoryDao categoryDao;

    public GetAdminCategoryController(CategoryDaoImpl categoryDaoIml) {
        this.categoryDao = categoryDaoIml;
    }

    @Override
    public ViewModel process(Request request) {
        ViewModel vm = ViewModel.of("adminCategories");
        List<Category> categories = categoryDao.findAll();
        vm.addAttribute("categories", categories);
        return vm;
    }
}
