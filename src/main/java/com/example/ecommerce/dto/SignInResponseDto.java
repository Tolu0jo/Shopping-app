package com.example.ecommerce.dto;

public class SignInResponseDto {

    public String id;
 public String status="SignIn Successful" ;
 public String token;


    public SignInResponseDto(String token,String id) {
        this.token = token;
        this.id=id;
    }
}
