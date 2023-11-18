package com.example.ecommerce.service;

import com.example.ecommerce.dto.AddToCartDto;
import com.example.ecommerce.dto.CartDto;
import com.example.ecommerce.dto.CartsDto;
import com.example.ecommerce.exceptions.ProductNotExistException;
import com.example.ecommerce.exceptions.UserException;
import com.example.ecommerce.mappers.CartMapper;
import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final UserService userService;

    private final ProductService productService;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, UserService userService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public CartDto addToCart(String userId, AddToCartDto addToCartDto){
        User user = userService.getUserDetails(userId);
        Product product= productService.getProductDetails(addToCartDto.getProductId());
        if(Objects.isNull(product)) throw new ProductNotExistException("product not found");
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProduct(product);
        cart.setQuantity(addToCartDto.getQuantity());
        Cart newCart = cartRepository.save(cart);
        return CartMapper.mapTOCartDto(newCart);
    }
    public CartsDto getCartItems(String userId){
        User user = userService.getUserDetails(userId);
        List<Cart> cartList= cartRepository.findAllByUserOrderByCreatedAtDesc(user);
       List< CartDto> cartDto = cartList.stream().map(CartMapper::mapTOCartDto).toList();
       CartsDto cartsDto = new CartsDto();
       cartsDto.setCartDtos(cartDto);
       cartsDto.setTotalCartPrice(cartList.stream().mapToDouble(cart ->cart.getQuantity()*cart.getProduct().getPrice()).sum()  );
       return cartsDto;
    }

    @Override
    public String deleteCartItem(String userId, String cartId) {
        User user = userService.getUserDetails(userId);
        Optional<Cart> cart = cartRepository.findById(cartId);
       if(cart.isEmpty()) throw new UserException("cart item with "+cartId +" not found");
       if(cart.get().getUser()!= user)throw new UserException("cart item does not belong to user");
       cartRepository.delete(cart.get());
       return cartId +" deleted Successfully";
    }
}
