package com.valiksk8.controller;

import com.valiksk8.dao.CategoryDaoImpl;
import com.valiksk8.model.Category;
import com.valiksk8.web.Request;
import com.valiksk8.web.ViewModel;

import java.util.List;

public class GetAllCategoriesController implements Controller {

    private final CategoryDaoImpl categoryDaoImp;

    public GetAllCategoriesController(CategoryDaoImpl categoryDaoImp) {
        this.categoryDaoImp = categoryDaoImp;
    }

    @Override
    public ViewModel process(Request request) {
        List<Category> categories = categoryDaoImp.findAll();
        ViewModel vm = ViewModel.of("categories");
        vm.addAttribute("categories", categories);
        return vm;
    }
}
