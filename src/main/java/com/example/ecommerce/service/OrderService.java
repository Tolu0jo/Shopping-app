package com.example.ecommerce.service;

import com.example.ecommerce.dto.CartDto;
import com.example.ecommerce.dto.CheckOutDto;
import com.example.ecommerce.dto.StripeResponse;
import com.stripe.exception.StripeException;

import java.util.List;

public interface OrderService {
StripeResponse createSession(List<CartDto> cartDtos) throws StripeException;
}
