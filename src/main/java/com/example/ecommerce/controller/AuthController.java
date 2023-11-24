package com.example.ecommerce.controller;


import com.example.ecommerce.dto.SignInDto;
import com.example.ecommerce.dto.SignInResponseDto;
import com.example.ecommerce.dto.SignUpDto;
import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("admin/signup")
    public ResponseEntity<SignUpDto> signUpAdmin (@RequestBody SignUpDto signUpDto){
        SignUpDto newSignUpDto= authService.signUpAdmin(signUpDto);
        return new ResponseEntity<>(newSignUpDto, HttpStatus.CREATED);
    }
    @PostMapping("signup")
    public ResponseEntity<SignUpDto> signUp (@RequestBody SignUpDto signUpDto){
        SignUpDto newSignUpDto= authService.signUp(signUpDto);
     return new ResponseEntity<>(newSignUpDto, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponseDto> signIn (@RequestBody SignInDto signInDto){

        SignInResponseDto signInDtoResponse= authService.signIn(signInDto);
        return ResponseEntity.ok(signInDtoResponse);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUser (@PathVariable String id){
          UserDto user = authService.getUser(id);
        return ResponseEntity.ok(user);
    }
}
