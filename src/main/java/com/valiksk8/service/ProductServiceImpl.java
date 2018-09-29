package com.valiksk8.service;


import com.valiksk8.model.Product;

import java.util.List;
import com.valiksk8.dao.ProuductDao;

public class ProductServiceImpl implements ProductService {


    private final ProuductDao productDao;

    public ProductServiceImpl (ProuductDao productDao){
        this.productDao = productDao;
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public Product findByName(String name) {
        return productDao.findByName(name);
    }
//
//    @Override
//    public List<Product> findAll() {
//        return productDao.findAll();
//    }
}
