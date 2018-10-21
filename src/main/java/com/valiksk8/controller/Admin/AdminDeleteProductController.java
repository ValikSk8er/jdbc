package com.valiksk8.controller.Admin;

import com.valiksk8.controller.Controller;
import com.valiksk8.model.Product;
import com.valiksk8.service.ProductService;
import com.valiksk8.web.Request;
import com.valiksk8.web.ViewModel;
import jdk.jshell.spi.ExecutionControl;

import java.util.List;

public class AdminDeleteProductController implements Controller {
    private final ProductService productService;

    public AdminDeleteProductController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ViewModel process(Request request) {
        ViewModel vm = ViewModel.of("adminProducts");
        String productId = request.getParamByName("p_id");
        Long id = Long.parseLong(productId);

        productService.deleteById(id);
        vm.addAttribute("msg_del", true);

        List<Product> products = productService.findAll();
        vm.addAttribute("products", products);
        return vm;
    }
}
