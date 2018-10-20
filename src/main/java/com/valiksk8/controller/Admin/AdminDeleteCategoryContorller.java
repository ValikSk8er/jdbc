package com.valiksk8.controller.Admin;

import com.valiksk8.controller.Controller;
import com.valiksk8.model.Category;
import com.valiksk8.model.Product;
import com.valiksk8.service.CategoryService;
import com.valiksk8.service.ProductService;
import com.valiksk8.web.Request;
import com.valiksk8.web.ViewModel;

import java.util.List;

public class AdminDeleteCategoryContorller implements Controller {

    CategoryService categoryService;
    ProductService productService;

    public AdminDeleteCategoryContorller(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @Override
    public ViewModel process(Request request) {
        ViewModel vm;
        String categoryId = request.getParamByName("c_id");

        Long id = Long.parseLong(categoryId);
        vm = processDeleteCategoryById(id);

        List<Category> categories = categoryService.findAll();
        vm.addAttribute("categories", categories);
        return vm;
    }

    private ViewModel processDeleteCategoryById(Long id) {
        ViewModel vm = ViewModel.of("adminCategories");
        processClearCategoryIdInProducts(id);
        categoryService.deleteById(id);
        vm.addAttribute("msg_del", true);
        return vm;
    }

    private void processClearCategoryIdInProducts(Long categoryId) {
        List<Product> products = productService.findAllWithCategoryId(categoryId);
        for (Product product : products) {
            product.setCategoryId(null);
            Long productId = product.getId();
            productService.updateById(productId, product);
        }
    }
}
