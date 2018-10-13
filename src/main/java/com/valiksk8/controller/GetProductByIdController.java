package com.valiksk8.controller;


import com.valiksk8.dao.ProductDao;
import com.valiksk8.model.Product;
import com.valiksk8.web.Request;
import com.valiksk8.web.ViewModel;

public class GetProductByIdController implements Controller {

    private final ProductDao productDao;

    public GetProductByIdController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public ViewModel process(Request request) {
        Product product = productDao.findById(getIdFromRequest(request));
        ViewModel vm = ViewModel.of("product");
        vm.addAttribute("product", product);
        return vm;
    }


    private Long getIdFromRequest(Request request) {
        String iDobject = request.getParamByName("p_id");
        return Long.valueOf(iDobject);
    }
}
