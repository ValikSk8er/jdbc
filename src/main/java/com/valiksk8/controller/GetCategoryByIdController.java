package com.valiksk8.controller;

import com.valiksk8.dao.CategoryDao;
import com.valiksk8.dao.CategoryDaoImpl;
import com.valiksk8.model.Category;
import com.valiksk8.web.Request;
import com.valiksk8.web.ViewModel;

public class GetCategoryByIdController implements Controller{
    private final CategoryDaoImpl categoryDaoImpl;

    public GetCategoryByIdController(CategoryDaoImpl categoryDaoImpl) {
        this.categoryDaoImpl = categoryDaoImpl;
    }

    @Override
    public ViewModel process(Request request) {
        Category category = categoryDaoImpl.findById(getIdFromRequest(request));
        ViewModel vm = ViewModel.of("category");
        vm.addAttribute("category", category);
        return vm;
    }

    private Long getIdFromRequest(Request request) {
        String iDobject = request.getParamByName("c_id");
        return Long.valueOf(iDobject);
    }
}
