package com.example.ecommerce.controller;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.Wishlist;
import com.example.ecommerce.service.WishlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/wishlist")
public class WishlistController {
    private final  WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("{id}")
    public ResponseEntity<Product> addToWishList(@RequestBody Product product,@PathVariable("id") String id){
       return null;
    }

}
