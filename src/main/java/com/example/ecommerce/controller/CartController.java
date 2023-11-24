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

    @GetMapping
    public ResponseEntity<CartsDto> getCartItems(){
        CartsDto cartsDto = cartService.getCartItems();
        return ResponseEntity.ok(cartsDto);
    }
    @PostMapping("/addToCart")
    public ResponseEntity<CartDto> addToCart(@RequestBody AddToCartDto addToCartDto){
      CartDto cartDto = cartService.addToCart(addToCartDto);
    return ResponseEntity.ok(cartDto);
    }

    @DeleteMapping("/delete/{cartId}")
   public String deleteCartItem(@PathVariable String cartId){
      return cartService.deleteCartItem(cartId);
    }

}
