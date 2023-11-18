package com.example.ecommerce.dto;

import com.example.ecommerce.model.Product;

public class CartDto {

    private String id;
    private ProductDto product;

    private int quantity;

    private double unitPrice;
    private double totalPrice;


    public String getId() {
        return id;
    }

    public ProductDto getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
