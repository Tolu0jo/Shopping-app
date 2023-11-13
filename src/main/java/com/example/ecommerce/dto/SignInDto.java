package com.example.ecommerce.dto;

import jakarta.validation.constraints.NotEmpty;

public class SignInDto {

    @NotEmpty
    private String email;

    @NotEmpty
    private String Password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
