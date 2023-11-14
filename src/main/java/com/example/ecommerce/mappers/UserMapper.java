package com.example.ecommerce.mappers;

import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.dto.SignUpDto;
import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;

public class UserMapper {
    public static SignUpDto maptoSignUpDto(User user){
       SignUpDto signUpDto = new SignUpDto();
        signUpDto.setId(user.getId());
        signUpDto.setUserName(user.getUserName());
        signUpDto.setEmail(user.getEmail());
        signUpDto.setPassword(user.getPassword());
        return signUpDto;
    }
    public static UserDto maptoUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

}
