package com.example.ecommerce.controller;

import com.example.ecommerce.config.JwtAuthFilter;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.WishlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/wishlist")
public class WishlistController {
    private final  WishlistService wishlistService;
    private final JwtAuthFilter jwtAuthFilter;

    private final UserRepository userRepository;

    public WishlistController(WishlistService wishlistService, JwtAuthFilter jwtAuthFilter, UserRepository userRepository) {
        this.wishlistService = wishlistService;
        this.jwtAuthFilter = jwtAuthFilter;
        this.userRepository = userRepository;
    }

    @PostMapping("add/{productId}")
    public ResponseEntity<ProductDto> addToWishList(@PathVariable("productId")String productId){
      ProductDto product = wishlistService.addWishlist(productId);
       return ResponseEntity.ok(product);
    }

    @GetMapping("getWishlist")
    public ResponseEntity<List<ProductDto>> getWishList(){


        List<ProductDto> products = wishlistService.getUserWishlist();
        return ResponseEntity.ok(products);
    }

}
