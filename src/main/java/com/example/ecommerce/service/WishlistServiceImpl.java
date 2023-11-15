package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.mappers.ProductMapper;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import com.example.ecommerce.model.Wishlist;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishlistServiceImpl implements WishlistService {
    private final WishlistRepository wishlistRepository;
    private final UserService userService;


    private final ProductService productService;


    @Autowired
    public WishlistServiceImpl(WishlistRepository wishlistRepository, UserService userService, UserRepository userRepository, ProductService productService) {
        this.wishlistRepository = wishlistRepository;
        this.userService = userService;
        this.productService = productService;

    }

    public ProductDto addWishlist(String userId, String productId) {
//        Wishlist existingWishlist= wishlistRepository.findWishlist(userId,productId);
        //      if(!Objects.isNull(existingWishlist)) throw new UserException("Wishlist already exists");
        User user = userService.getUserDetails(userId);
        Product product = productService.getProductDetails(productId);
        Wishlist wishlist = new Wishlist();
        wishlist.setProduct(product);
        wishlist.setUser(user);
        wishlistRepository.save(wishlist);
        return ProductMapper.maptoProductDto(product);
    }

    public List<ProductDto> getUserWishlist(String userId) {
        User user = userService.getUserDetails(userId);
        List<Wishlist> wishlists = wishlistRepository.findAllByUserOrderByCreatedDateDesc(user);
        return wishlists.stream()
                .map(wishlist -> ProductMapper
                        .maptoProductDto(wishlist.getProduct()))
                .toList();
    }
}