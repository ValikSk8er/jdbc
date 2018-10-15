package com.valiksk8.controller;

import com.valiksk8.model.Product;
import com.valiksk8.service.ProductService;
import com.valiksk8.web.Request;
import com.valiksk8.web.ViewModel;

import java.util.List;

public class AdminProductsController implements Controller {

    private final ProductService productService;

    public AdminProductsController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ViewModel process(Request request) {
        List<Product> products = productService.findAll();
        ViewModel vm = ViewModel.of("adminProducts");
        vm.addAttribute("products", products);
        return vm;
    }
}
