package com.example.ecommerce.service;

import com.example.ecommerce.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {
    String generateToken(UserDetails user);

    String extractEmail(String token);

    boolean isTokenValid(String token, UserDetails user);

    String generateRefreshToken(Map<String,Object> extractClaims, UserDetails user);
}
