package com.example.ecommerce.dto;

import com.example.ecommerce.model.Cart;

import java.util.List;

public class CartsDto {

    private List<CartDto> cartDtos;

    private double TotalCartPrice;

    public List<CartDto> getCartDtos() {
        return cartDtos;
    }

    public void setCartDtos(List<CartDto> cartDtos) {
        this.cartDtos = cartDtos;
    }

    public double getTotalCartPrice() {
        return TotalCartPrice;
    }

    public void setTotalCartPrice(double totalCartPrice) {
        TotalCartPrice = totalCartPrice;
    }

}
