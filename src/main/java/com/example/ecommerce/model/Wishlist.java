package com.example.ecommerce.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name="wishlist")
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @Column(name = "user_id")
    private User user;

    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
