package com.example.ecommerce.controller;


import com.example.ecommerce.dto.SignInDto;
import com.example.ecommerce.dto.SignInResponseDto;
import com.example.ecommerce.dto.SignUpDto;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("signup")
    public ResponseEntity<SignUpDto> signUp (@RequestBody SignUpDto signUpDto){
        SignUpDto newSignUpDto=userService.signUp(signUpDto);
     return new ResponseEntity<>(newSignUpDto, HttpStatus.CREATED);
    }

    @PostMapping("signin")
    public ResponseEntity<SignInResponseDto> signIn (@RequestBody SignInDto signInDto){
        SignInResponseDto signInDtoResponse=userService.signIn(signInDto);
        return ResponseEntity.ok(signInDtoResponse);
    }
}
