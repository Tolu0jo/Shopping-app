package com.example.ecommerce.service;

import com.example.ecommerce.dto.SignInDto;
import com.example.ecommerce.dto.SignInResponseDto;
import com.example.ecommerce.dto.SignUpDto;
import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.exceptions.AuthenticationFailException;
import com.example.ecommerce.exceptions.UserException;
import com.example.ecommerce.mappers.UserMapper;
import com.example.ecommerce.model.AuthenticationToken;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.TokenRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Objects;
import java.util.Optional;


@Service
public class UserServiceImpl implements  UserService{
    private final UserRepository userRepository;

    private final TokenRepository tokenRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;

    }

    @Transactional
    public SignUpDto signUp(SignUpDto signUpDto){
        User existingUser = userRepository.findByEmail(signUpDto.getEmail());
        if(existingUser != null) throw new UserException("User already exist");
        User user = new User();
        user.setUserName(signUpDto.getUserName());
        user.setEmail(signUpDto.getEmail());
        String hashedPassword = signUpDto.getPassword();
       //Hash the password
        user.setPassword(hashedPassword);
        User newUser = userRepository.save(user);
        AuthenticationToken authenticationToken = new AuthenticationToken();
        authenticationToken.setUser(user);
        tokenRepository.save(authenticationToken);
       return UserMapper.maptoSignUpDto(newUser);
    }

    @Override
    public SignInResponseDto signIn(SignInDto signInDto){
        User existingUser = userRepository.findByEmail(signInDto.getEmail());
        if(Objects.isNull(existingUser)) throw new AuthenticationFailException("Kindly Signup");
        if(!existingUser.getPassword().equals(signInDto.getPassword())) throw new AuthenticationFailException("Invalid credentials");
        AuthenticationToken token = tokenRepository.findByUser(existingUser);
         if(Objects.isNull(token)) throw new UserException("Token not found");
       return new SignInResponseDto(token.getToken());
    }

    @Override
    public UserDto getUser(String id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty())throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"User not found");
       return UserMapper.maptoUserDto(user.get());
    }
}
