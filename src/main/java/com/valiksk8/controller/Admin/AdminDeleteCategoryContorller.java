package com.valiksk8.controller.Admin;

import com.valiksk8.controller.Controller;
import com.valiksk8.model.Category;
import com.valiksk8.service.CategoryService;
import com.valiksk8.web.Request;
import com.valiksk8.web.ViewModel;

import java.util.List;

public class AdminDeleteCategoryContorller implements Controller {

    CategoryService categoryService;

    public AdminDeleteCategoryContorller(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public ViewModel process(Request request) {
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

        List<Category> categories = categoryService.findAll();
        vm.addAttribute("categories", categories);
        return vm;
    }

    private ViewModel processDeleteCategoryById(String categoryId) {
        ViewModel vm = ViewModel.of("adminCategories");
        Long id = Long.parseLong(categoryId);
        boolean isNotExist = categoryService.findById(id) == null;

        if (isNotExist) {
            vm.addAttribute("msg_delete_id", true);
        } else {
            categoryService.deleteById(id);
            vm.addAttribute("msg_delete_id_success", true);
        }
        return vm;
    }

    private ViewModel processDeleteCategoryByName(String categoryName) {
        ViewModel vm = ViewModel.of("adminCategories");
        boolean isNotExist = categoryService.findByName(categoryName) == null;

        if (isNotExist) {
            vm.addAttribute("msg_delete_name", true);
        } else {
            categoryService.deleteByName(categoryName);
            vm.addAttribute("msg_delete_name_success", true);
        }
        return vm;
    }
}
