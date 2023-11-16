package com.example.ecommerce.service;

import com.example.ecommerce.dto.AddToCartDto;
import com.example.ecommerce.dto.CartDto;
import com.example.ecommerce.dto.CartsDto;

public interface CartService {
    CartDto addToCart(String userId, AddToCartDto addToCartDto);
    CartsDto getCartItems(String userId);
}
