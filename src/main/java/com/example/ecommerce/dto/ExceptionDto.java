package com.example.ecommerce.dto;

public class ExceptionDto {
    private String error;

    public String getError() {
        return error;
    }

    public ExceptionDto(String error) {
        this.error = error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
