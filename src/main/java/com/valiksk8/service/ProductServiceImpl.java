package com.valiksk8.service;


import com.valiksk8.model.Product;

import com.valiksk8.dao.ProductDao;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    public ProductServiceImpl (ProductDao productDao){
        this.productDao = productDao;
    }

    @Override
    public Product findById(Long id) {
        return productDao.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public void add(Product product) {
        productDao.add(product);
    }

    @Override
    public void updateById(Long id, Product product) {
        productDao.updateById(id, product);
    }

    @Override
    public void deleteById(Long id) {
        productDao.deleteById(id);
    }

    @Override
    public Product findByName(String name) {
        return productDao.findByName(name);
    }

    @Override
    public List<Product> findAllWithCategoryId(Long id) {
        return productDao.findAllWithCategoryId(id);
    }
}
