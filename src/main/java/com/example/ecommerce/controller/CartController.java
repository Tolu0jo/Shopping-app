package com.example.ecommerce.controller;

import com.example.ecommerce.dto.AddToCartDto;
import com.example.ecommerce.dto.CartDto;
import com.example.ecommerce.dto.CartsDto;
import com.example.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cart")
public class CartController {
private final CartService cartService;

@Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/addToCart/{userId}")
    public ResponseEntity<CartDto> addToCart(@PathVariable String userId,
                                             @RequestBody AddToCartDto addToCartDto){
      CartDto cartDto = cartService.addToCart(userId,addToCartDto);
    return ResponseEntity.ok(cartDto);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartsDto> getCartItems(@PathVariable("userId") String userId){
      CartsDto cartsDto = cartService.getCartItems(userId);
      return ResponseEntity.ok(cartsDto);
    }


}
