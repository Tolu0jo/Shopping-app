package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CartDto;
import com.example.ecommerce.dto.StripeResponse;
import com.example.ecommerce.service.OrderService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<StripeResponse> checkOutList(@RequestBody List<CartDto> cartDtos) throws StripeException {
     StripeResponse stripeResponse=orderService.createSession(cartDtos);
     return ResponseEntity.ok(stripeResponse);
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) {

        orderService.handleWebhookEvent(payload, sigHeader);

        return ResponseEntity.status(HttpStatus.OK).body("Webhook handled successfully");
    }

}
