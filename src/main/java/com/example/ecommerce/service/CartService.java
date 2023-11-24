package com.example.ecommerce.service;

import com.example.ecommerce.dto.AddToCartDto;
import com.example.ecommerce.dto.CartDto;
import com.example.ecommerce.dto.CartsDto;
import com.example.ecommerce.model.User;

public interface CartService {
    CartDto addToCart(AddToCartDto addToCartDto);
    CartsDto getCartItems();
    String deleteCartItem(String cartId);
    void deleteCartItemByWebHook(User user);
}
