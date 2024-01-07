package com.endproject.javafundamentalsendproject.Model;


import java.io.Serializable;

public class OrderItem implements Serializable {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return product.getName();
    }
    public int getStock() {
        return product.getStock();
    }

    public String getCategory() {
        return product.getCategory();
    }

    public double getPrice() {
        return product.getPrice();
    }


    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
