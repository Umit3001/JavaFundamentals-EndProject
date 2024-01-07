package com.endproject.javafundamentalsendproject.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {

    private int orderId;
    private String customerFirstName;
    private String customerLastName;
    private String customerEmailAddress;
    private String customerPhoneNumber;
    private List<OrderItem> customerProduct;
    private String dateTime;
    private double totalPrice;

    public Order(int orderId, String customerFirstName, String customerLastName, String customerEmailAddress, String customerPhoneNumber, List<OrderItem> customerProduct, String dateTime, double totalPrice) {
        this.orderId = orderId;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerEmailAddress = customerEmailAddress;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerProduct = new ArrayList<>(customerProduct);
        this.dateTime = dateTime;
        this.totalPrice = totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerEmailAddress() {
        return customerEmailAddress;
    }

    public void setCustomerEmailAddress(String customerEmailAddress) {
        this.customerEmailAddress = customerEmailAddress;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public List<OrderItem> getCustomerProduct() {
        return customerProduct;
    }

    public void setCustomerProduct(List<OrderItem> customerProduct) {
        this.customerProduct = customerProduct;
    }

    public void addProduct(OrderItem orderItem) {
        customerProduct.add(orderItem);
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
