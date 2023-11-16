package com.example.ecommerce.mappers;

import com.example.ecommerce.dto.CartDto;
import com.example.ecommerce.model.Cart;

public class CartMapper {
    public  static CartDto mapTOCartDto(Cart cart){
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setProduct(ProductMapper.maptoProductDto(cart.getProduct()));
        cartDto.setQuantity(cart.getQuantity());
        cartDto.setUnitPrice(cart.getProduct().getPrice());
        cartDto.setTotalPrice(cart.getQuantity()*cart.getProduct().getPrice());
        return cartDto;
    }
}
