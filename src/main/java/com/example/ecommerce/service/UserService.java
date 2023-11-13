package com.example.ecommerce.service;

import com.example.ecommerce.dto.SignInDto;
import com.example.ecommerce.dto.SignInResponseDto;
import com.example.ecommerce.dto.SignUpDto;

public interface UserService {
    SignUpDto signUp(SignUpDto signUpDto);
    SignInResponseDto signIn(SignInDto signInDto);
}
