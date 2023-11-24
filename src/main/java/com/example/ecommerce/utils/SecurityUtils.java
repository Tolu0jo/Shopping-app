package com.example.ecommerce.utils;

import com.example.ecommerce.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;



    public class SecurityUtils {
        public static User getCurrentUserDetails() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            Object principal = authentication.getPrincipal();

            return (User) principal;

        }

    }