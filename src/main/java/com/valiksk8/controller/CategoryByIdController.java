package com.valiksk8.controller;

import com.valiksk8.model.Category;
import com.valiksk8.service.CategoryService;
import com.valiksk8.web.Request;
import com.valiksk8.web.ViewModel;

public class CategoryByIdController implements Controller{
    private final CategoryService categoryService;

    public CategoryByIdController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public ViewModel process(Request request) {
        Category category = categoryService.findWithProductById(getIdFromRequest(request));
        ViewModel vm = ViewModel.of("category");
        vm.addAttribute("category", category);
        return vm;
    }

    private Long getIdFromRequest(Request request) {
        String iDobject = request.getParamByName("c_id");
        return Long.valueOf(iDobject);
    }
}
