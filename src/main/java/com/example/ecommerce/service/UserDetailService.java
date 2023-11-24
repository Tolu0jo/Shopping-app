package com.example.ecommerce.service;

import com.example.ecommerce.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDetailService {

    UserDetailsService userDetailsService();

}
