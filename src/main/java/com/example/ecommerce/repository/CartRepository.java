package com.example.ecommerce.repository;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,String> {

    List<Cart> findAllByUserOrderByCreatedAtDesc(User user);

    List<Cart> findAllByProduct(Product product);

    void deleteCartsByUser(User user);
}
