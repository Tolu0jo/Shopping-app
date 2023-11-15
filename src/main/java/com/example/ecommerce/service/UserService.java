package com.example.ecommerce.service;

import com.example.ecommerce.dto.SignInDto;
import com.example.ecommerce.dto.SignInResponseDto;
import com.example.ecommerce.dto.SignUpDto;
import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.model.User;

public interface UserService {
    SignUpDto signUp(SignUpDto signUpDto);
    SignInResponseDto signIn(SignInDto signInDto);

    UserDto getUser(String id);

    User getUserDetails(String id);
}
