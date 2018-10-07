package com.valiksk8.model;

import com.valiksk8.metadata.ColumnName;
import com.valiksk8.metadata.TableName;

@TableName("PRODUCTS")
public class Product {

    @ColumnName("ID")
    private Long id;

    @ColumnName("NAME")
    private String name;

    @ColumnName("PRICE")
    private double price;

    @ColumnName("DESCRIPTION")
    private String description;

    @ColumnName("FK_CATEGORIES")
    private Long category_id;
    private Category category;


    public Product(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Product(Long id, String name, double price, String description) {
        this(name, price, description);
        this.id = id;
    }

    public Product(String name, double price, String description, Category category) {
        this(name, price, description);
        this.category = category;
        this.category_id = category.getId();
    }

    public Category getCategory() {
        return category;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}