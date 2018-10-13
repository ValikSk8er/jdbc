package com.valiksk8.service;


import com.valiksk8.model.Product;

import com.valiksk8.dao.ProductDao;

public class ProductServiceImpl implements ProductService {


    private final ProductDao productDao;

    public ProductServiceImpl (ProductDao productDao){
        this.productDao = productDao;
    }

    @Override
    public void add(Product product) {
        productDao.add(product);
    }

    @Override
    public Product findByName(String name) {
        return productDao.findByName(name);
    }

}
