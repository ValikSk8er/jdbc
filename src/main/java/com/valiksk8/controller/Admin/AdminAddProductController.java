package com.valiksk8.controller.Admin;

import com.valiksk8.controller.Controller;
import com.valiksk8.model.Category;
import com.valiksk8.model.Product;
import com.valiksk8.service.CategoryService;
import com.valiksk8.service.ProductService;
import com.valiksk8.web.Request;
import com.valiksk8.web.ViewModel;

import java.util.List;

public class AdminAddProductController implements Controller {

    private final ProductService productService;
    private final CategoryService categoryService;


    public AdminAddProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public ViewModel process(Request request) {
        ViewModel vm = ViewModel.of("adminProducts");
        String name = request.getParamByName("name");
        boolean isExist = productService.findByName(name) != null;

        if (isExist) {
            vm.addAttribute("msg_add_error", true);
        } else {
            processAddProduct(request);
            vm.addAttribute("msg_add_success", true);
        }
        List<Product> products = productService.findAll();
        vm.addAttribute("products", products);
        return vm;
    }

    private void processAddProduct(Request request) {
        String name = request.getParamByName("name");
        String description = request.getParamByName("description");
        String priceString = request.getParamByName("price");
        String categoryIdString = request.getParamByName("categoryId");
        double price = Double.parseDouble(priceString);

        Long categoryId = Long.parseLong(categoryIdString);

        Category category = categoryService.findById(categoryId);
        Product product = new Product(name, price, description, category);

        productService.add(product);
    }
}
