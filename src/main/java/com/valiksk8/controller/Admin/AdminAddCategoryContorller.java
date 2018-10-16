package com.valiksk8.controller.Admin;

import com.valiksk8.controller.Controller;
import com.valiksk8.model.Category;
import com.valiksk8.service.CategoryService;
import com.valiksk8.web.Request;
import com.valiksk8.web.ViewModel;

import java.util.List;

public class AdminAddCategoryContorller implements Controller {

    public AdminAddCategoryContorller(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    CategoryService categoryService;

    @Override
    public ViewModel process(Request request) {
        ViewModel vm = ViewModel.of("adminCategories");
        String categoryName = request.getParamByName("categoryName");
        boolean isExist = categoryService.findByName(categoryName) != null;

        if (isExist) {
            vm.addAttribute("msg_add", true);
        } else {
            categoryService.addByName(categoryName);
            vm.addAttribute("msg_add_success", true);
        }
        List<Category> categories = categoryService.findAll();
        vm.addAttribute("categories", categories);
        return vm;
    }
}
