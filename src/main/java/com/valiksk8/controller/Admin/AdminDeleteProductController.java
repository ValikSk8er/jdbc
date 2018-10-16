package com.valiksk8.controller.Admin;

import com.valiksk8.controller.Controller;
import com.valiksk8.model.Product;
import com.valiksk8.service.ProductService;
import com.valiksk8.web.Request;
import com.valiksk8.web.ViewModel;

import java.util.List;

public class AdminDeleteProductController implements Controller {
    private final ProductService productService;

    public AdminDeleteProductController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ViewModel process(Request request) {
        ViewModel vm = ViewModel.of("adminProducts");
        String productIdString = request.getParamByName("productId");
        Long productId = Long.parseLong(productIdString);

        boolean isNotExist = productService.findById(productId) == null;
        if (isNotExist) {
            vm.addAttribute("msg_delete_error", true);
        } else {
            productService.deleteById(productId);
            vm.addAttribute("msg_delete_success", true);
        }
        List<Product> products = productService.findAll();
        vm.addAttribute("products", products);
        return vm;    }
}
