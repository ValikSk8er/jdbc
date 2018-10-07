package com.valiksk8.controller;


import com.valiksk8.dao.ProductDaoImpl;
import com.valiksk8.model.Product;
import com.valiksk8.web.Request;
import com.valiksk8.web.ViewModel;

public class GetProductByIdController implements Controller {

    private final ProductDaoImpl productDaoImpl;

    public GetProductByIdController(ProductDaoImpl productDaoImpl) {
        this.productDaoImpl = productDaoImpl;
    }

    @Override
    public ViewModel process(Request request) {
        Product product = productDaoImpl.findById(getIdFromRequest(request));
        ViewModel vm = ViewModel.of("product");
        vm.addAttribute("product", product);
        return vm;
    }


    private Long getIdFromRequest(Request request) {
        String iDobject = request.getParamByName("p_id");
        return Long.valueOf(iDobject);
    }
}
