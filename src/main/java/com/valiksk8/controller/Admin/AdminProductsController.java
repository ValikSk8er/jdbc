package com.valiksk8.controller.Admin;

import com.valiksk8.controller.Controller;
import com.valiksk8.model.Category;
import com.valiksk8.model.Product;
import com.valiksk8.service.CategoryService;
import com.valiksk8.service.ProductService;
import com.valiksk8.web.Request;
import com.valiksk8.web.ViewModel;

import java.util.List;

public class AdminProductsController implements Controller {

    private final ProductService productService;
    private final CategoryService categoryService;

    public AdminProductsController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public ViewModel process(Request request) {
        List<Product> products = productService.findAll();
        ViewModel vm = ViewModel.of("adminProducts");
        List<Category> categories = categoryService.findAll();
        vm.addAttribute("categories", categories);
        vm.addAttribute("products", products);
        return vm;
    }
}
