package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.JwtAuthFilter;
import com.example.ecommerce.dto.AddToCartDto;
import com.example.ecommerce.dto.CartDto;
import com.example.ecommerce.dto.CartsDto;
import com.example.ecommerce.exceptions.NotFoundException;
import com.example.ecommerce.exceptions.BadRequestException;
import com.example.ecommerce.mappers.CartMapper;
import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.service.AuthService;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;


    private final ProductService productService;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    @Override
    public CartDto addToCart( AddToCartDto addToCartDto){
        User user = SecurityUtils.getCurrentUserDetails();
        Product product= productService.getProductDetails(addToCartDto.getProductId());
        if(Objects.isNull(product)) throw new NotFoundException("product not found");
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProduct(product);
        cart.setQuantity(addToCartDto.getQuantity());
        Cart newCart = cartRepository.save(cart);
        return CartMapper.mapTOCartDto(newCart);
    }
    public CartsDto getCartItems(){
        User user = SecurityUtils.getCurrentUserDetails();
        List<Cart> cartList= cartRepository.findAllByUserOrderByCreatedAtDesc(user);
       List< CartDto> cartDto = cartList.stream().map(CartMapper::mapTOCartDto).toList();
       CartsDto cartsDto = new CartsDto();
       cartsDto.setCartDtos(cartDto);
       cartsDto.setTotalCartPrice(cartList.stream().mapToDouble(cart ->cart.getQuantity()*cart.getProduct().getPrice()).sum()  );
       return cartsDto;
    }

    @Override
    public String deleteCartItem( String cartId) {
        User user = SecurityUtils.getCurrentUserDetails();
        Optional<Cart> cart = cartRepository.findById(cartId);
       if(cart.isEmpty()) throw new BadRequestException("cart item with "+cartId +" not found");
       if(cart.get().getUser()!= user)throw new BadRequestException("cart item does not belong to user");
       cartRepository.delete(cart.get());
       return cartId +" deleted Successfully";
    }

    @Override
    public void deleteCartItemByWebHook(User user){
          cartRepository.deleteCartsByUser(user);
    }
}
