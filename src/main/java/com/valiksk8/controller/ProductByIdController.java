package com.valiksk8.controller;


import com.valiksk8.model.Product;
import com.valiksk8.service.ProductService;
import com.valiksk8.web.Request;
import com.valiksk8.web.ViewModel;

public class ProductByIdController implements Controller {

    private final ProductService productService;

    public ProductByIdController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ViewModel process(Request request) {
        Product product = productService.findById(getIdFromRequest(request));
        ViewModel vm = ViewModel.of("product");
        vm.addAttribute("product", product);
        return vm;
    }

    private Long getIdFromRequest(Request request) {
        String iDobject = request.getParamByName("p_id");
        return Long.valueOf(iDobject);
    }
}