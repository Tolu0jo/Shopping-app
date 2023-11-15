package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductDto;

import java.util.List;

public interface WishlistService {
    ProductDto addWishlist(String userId, String productId);
    List<ProductDto> getUserWishlist(String userId);
}
