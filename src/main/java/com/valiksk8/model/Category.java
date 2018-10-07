package com.valiksk8.model;

import com.valiksk8.metadata.ColumnName;
import com.valiksk8.metadata.TableName;

import java.util.List;

@TableName("CATEGORIES")
public class Category {

    @ColumnName("ID")
    private Long id;

    @ColumnName("CATEGORY_NAME")
    private String name;

    private List<Product> products;


    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(Long id, String name, List<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}