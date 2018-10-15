package com.valiksk8.controller;

import com.valiksk8.model.Category;
import com.valiksk8.service.CategoryService;
import com.valiksk8.web.Request;
import com.valiksk8.web.ViewModel;

import java.util.List;

public class GetAdminAddCategoryContorller implements Controller {

    public GetAdminAddCategoryContorller(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    CategoryService categoryService;

    @Override
    public ViewModel process(Request request) {
        ViewModel vm = ViewModel.of("adminCategories");
        String categoryName = request.getParamByName("categoryName");
        boolean isExist = categoryService.checkCategoryExist(categoryName);

        if (isExist) {
            vm.addAttribute("msg_add", true);
        } else {
            categoryService.addByName(categoryName);
        }
        List<Category> categories = categoryService.findAll();
        vm.addAttribute("categories", categories);
        return vm;
    }

}
