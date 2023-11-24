package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.model.User;

import java.util.List;

public interface WishlistService {
    ProductDto addWishlist(String productId);
    List<ProductDto> getUserWishlist();
}
