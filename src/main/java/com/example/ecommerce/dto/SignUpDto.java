package com.example.ecommerce.dto;

import jakarta.validation.constraints.NotEmpty;

public class SignUpDto {

    private String id;
    @NotEmpty
    private String userName;

    @NotEmpty
    private String email;

    @NotEmpty
    private String Password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
