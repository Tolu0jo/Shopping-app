package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.service.WishlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/wishlist")
public class WishlistController {
    private final  WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("add/{id}/{productId}")
    public ResponseEntity<ProductDto> addToWishList(@PathVariable("id") String userId, @PathVariable("productId")String productId){
      ProductDto product = wishlistService.addWishlist(userId,productId);
       return ResponseEntity.ok(product);
    }

    @GetMapping("getWishlist/{userId}")
    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable String userId){
        List<ProductDto> products = wishlistService.getUserWishlist(userId);
        return ResponseEntity.ok(products);
    }

}
