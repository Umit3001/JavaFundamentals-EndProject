package com.endproject.javafundamentalsendproject.Model;

import java.io.Serializable;

public class Product implements Serializable {

    private int productId;
    private int stock;
    private String name;
    private String category;
    private double price;
    private String description;


    public Product(int productId, int stock, String name, String category, double price, String description) {
        this.productId = productId;
        this.stock = stock;
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
    }

    public int getProductId() {
        return productId;
    }

    public int getStock() {
        return stock;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
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
}

