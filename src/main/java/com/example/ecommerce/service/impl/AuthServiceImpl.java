package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.SignInDto;
import com.example.ecommerce.dto.SignInResponseDto;
import com.example.ecommerce.dto.SignUpDto;
import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.exceptions.AuthenticationFailException;
import com.example.ecommerce.exceptions.BadRequestException;
import com.example.ecommerce.mappers.UserMapper;
import com.example.ecommerce.model.Role;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.AuthService;
import com.example.ecommerce.service.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;


@Service
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;
    private final UserRepository userRepository;


    @Autowired
    public AuthServiceImpl(JwtService jwtService, UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager  authenticationManager) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }


    public SignUpDto signUp(SignUpDto signUpDto) {
        User existingUser = userRepository.findByEmail(signUpDto.getEmail());
        if (existingUser != null) throw new BadRequestException("User already exist");
        User user = new User();
        user.setUserName(signUpDto.getUserName());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setRole(Role.USER);
        User newUser = userRepository.save(user);
        return UserMapper.maptoSignUpDto(newUser);
    }

    @Override
    public SignUpDto signUpAdmin(SignUpDto signUpDto) {
        User existingUser = userRepository.findByEmail(signUpDto.getEmail());
        if (existingUser != null) throw new BadRequestException("User already exist");
        User user = new User();
        user.setUserName(signUpDto.getUserName());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setRole(Role.ADMIN);
        User newUser = userRepository.save(user);
        return UserMapper.maptoSignUpDto(newUser);
    }

    @Override
    public SignInResponseDto signIn(SignInDto signInDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDto.getEmail(), signInDto.getPassword()));
        User user = userRepository.findByEmail(signInDto.getEmail());
        if (Objects.isNull(user)) throw new AuthenticationFailException("Kindly Signup");
        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        SignInResponseDto signInResponseDto = new SignInResponseDto();
        signInResponseDto.setId(user.getId());
        signInResponseDto.setToken(token);
        signInResponseDto.setRole(user.getRole());
        signInResponseDto.setRefreshToken(refreshToken);
        return signInResponseDto;
    }

    @Override
    public UserDto getUser(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new AuthenticationFailException("User not found");
        return UserMapper.maptoUserDto(user.get());
    }

    @Override
    public User getUserDetails(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new AuthenticationFailException("User not found");;
        return user.get();
    }

}
