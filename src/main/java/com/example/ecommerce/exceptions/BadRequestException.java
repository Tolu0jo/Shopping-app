package com.example.ecommerce.exceptions;

public class BadRequestException extends IllegalArgumentException{
public BadRequestException(String msg){
    super(msg);
}
}
