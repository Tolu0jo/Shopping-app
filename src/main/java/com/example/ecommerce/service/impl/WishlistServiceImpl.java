package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.JwtAuthFilter;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.exceptions.BadRequestException;
import com.example.ecommerce.mappers.ProductMapper;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import com.example.ecommerce.model.Wishlist;
import com.example.ecommerce.repository.WishlistRepository;
import com.example.ecommerce.service.AuthService;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.service.WishlistService;
import com.example.ecommerce.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class WishlistServiceImpl implements WishlistService {
    private final WishlistRepository wishlistRepository;
    private final ProductService productService;


    @Autowired
    public WishlistServiceImpl(WishlistRepository wishlistRepository, ProductService productService) {
        this.wishlistRepository = wishlistRepository;
        this.productService = productService;

    }

    public ProductDto addWishlist(String productId) {
        Product product = productService.getProductDetails(productId);
        User user = SecurityUtils.getCurrentUserDetails();
        List<Wishlist> existingWishlist= wishlistRepository.findAllByProductAndUser(product,user);
        if (existingWishlist.stream()
                .anyMatch(wishlist -> Objects.equals(wishlist.getProduct().getId(), productId))) {
            throw new BadRequestException("Product already exists in wishlist");
        }
        Wishlist wishlist = new Wishlist();
        wishlist.setProduct(product);
        wishlist.setUser(user);
        wishlistRepository.save(wishlist);
        return ProductMapper.maptoProductDto(product);
    }

    @Override
    public List<ProductDto> getUserWishlist() {
        User user = SecurityUtils.getCurrentUserDetails();
        List<Wishlist> wishlists = wishlistRepository.findAllByUserOrderByCreatedDateDesc(user);
        return wishlists.stream()
                .map(wishlist -> ProductMapper
                        .maptoProductDto(wishlist.getProduct()))
                .toList();
    }
}