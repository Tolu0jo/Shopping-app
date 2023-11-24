package com.example.ecommerce.exceptions;

import com.example.ecommerce.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(value= BadRequestException.class)
    public final ResponseEntity<ExceptionDto> handleUserException(BadRequestException exception){
        return new ResponseEntity<>(new ExceptionDto(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value=AuthenticationFailException.class)
    public final ResponseEntity<ExceptionDto> handleAuthenticationFailException(AuthenticationFailException exception){
        return new ResponseEntity<>(new ExceptionDto(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value= NotFoundException.class)
    public final ResponseEntity<ExceptionDto> handleProductNotExistException(NotFoundException exception){
        return new ResponseEntity<>(new ExceptionDto(exception.getMessage()), HttpStatus.NOT_FOUND
        );
    }
}
