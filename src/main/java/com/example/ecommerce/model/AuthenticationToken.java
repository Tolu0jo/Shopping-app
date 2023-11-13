package com.example.ecommerce.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity(name="tokens")
public class AuthenticationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String token;

    private Date createdAt;


    @OneToOne (targetEntity = User.class,fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name="user_id")
    User user;

    public AuthenticationToken(){
        this.createdAt = new Date();
        this.token= UUID.randomUUID().toString();
    }



    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
