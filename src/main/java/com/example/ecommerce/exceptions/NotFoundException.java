package com.example.ecommerce.exceptions;

public class NotFoundException extends IllegalArgumentException{
    public NotFoundException(String productNotFound) {
    super(productNotFound);
    }
}
