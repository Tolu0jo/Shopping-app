package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CartDto;
import com.example.ecommerce.dto.CheckOutDto;
import com.example.ecommerce.dto.StripeResponse;
import com.example.ecommerce.service.OrderService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create-checkout")
    public ResponseEntity<StripeResponse> checkOutList(
           @RequestBody List<CartDto> cartDtos
                                               ) throws StripeException {
     StripeResponse stripeResponse=orderService.createSession(cartDtos);
     return ResponseEntity.ok(stripeResponse);
    }
}
