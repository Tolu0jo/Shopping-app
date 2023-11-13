package com.example.ecommerce.dto;

public class SignInResponseDto {
 public String status="SignUp Successful" ;
 public String token;

    public SignInResponseDto(String token) {
        this.token = token;
    }
}
