package com.example.ecommerce.repository;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import com.example.ecommerce.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist,String> {
  List<Wishlist> findAllByProductAndUser(Product product, User user);

    List <Wishlist> findAllByProduct(Product product);
    List<Wishlist> findAllByUserOrderByCreatedDateDesc(User user);
}
