package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.CartDto;
import com.example.ecommerce.dto.StripeResponse;

import com.example.ecommerce.model.User;

import com.example.ecommerce.service.AuthService;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.OrderService;
import com.example.ecommerce.utils.SecurityUtils;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @Value("${stripe.endpoint.secret}")
    private String endPointSecret;




    private final AuthService authService;

    private final CartService cartService;

    public OrderServiceImpl( AuthService authService, CartService cartService) {


        this.authService = authService;
        this.cartService = cartService;
    }

    @Override
    public StripeResponse createSession(List<CartDto> cartDtos) throws StripeException {
        User user = SecurityUtils.getCurrentUserDetails();
        String userId = user.getId();


        String successUrl = "http://127.0.0.1:8080/api/order/success/" + userId;
        String failureUrl = "http://127.0.0.1:8080/fail";
        Stripe.apiKey = stripeSecretKey;


        Map<String, String> metadataParams = new HashMap<>();
        metadataParams.put("userId", userId);


        List<SessionCreateParams.LineItem> sessionItemList = new ArrayList<>();

        for (CartDto cartDto : cartDtos) {
            sessionItemList.add(createSessionLineItem(cartDto));
        }

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl(failureUrl)
                .setSuccessUrl(successUrl)
                .addAllLineItem(sessionItemList)
                .putMetadata("metadata", userId)
                .putAllMetadata(metadataParams)
                .build();
        Session sessionDetails = Session.create(params);

        StripeResponse stripeResponse = new StripeResponse();
        stripeResponse.setSessionId(sessionDetails.getId());
        stripeResponse.setSessionUrl(sessionDetails.getUrl());

        return stripeResponse;

    }


    private SessionCreateParams.LineItem createSessionLineItem(CartDto cartDto) {
        return SessionCreateParams.LineItem.builder()
                .setPriceData(createPriceData(cartDto)).
                setQuantity(Long.parseLong(String.valueOf(cartDto.getQuantity()))).build();
    }

    private SessionCreateParams.LineItem.PriceData createPriceData(CartDto cartDto) {
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("ngn")
                .setUnitAmount((long) cartDto.getUnitPrice() * 100)
                .setProductData(
                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(cartDto.getProduct().getProductName())
                                .build()
                ).build();

    }


    @Override
    public void handleWebhookEvent(String payload, String sigHeader) {
        try {
            System.out.println("Received payload: " + payload);
            System.out.println("Received signature header: " + sigHeader);
            Stripe.apiKey = stripeSecretKey;
            Event event = Webhook.constructEvent(payload, sigHeader, endPointSecret);
            handleEvent(event);
        } catch (StripeException e) {
            System.err.println("Error verifying signature: " + e.getMessage());
            throw new RuntimeException(e);

        }

    }

    private void handleEvent(Event event) throws StripeException {
        Stripe.apiKey = stripeSecretKey;
        if ("payment_intent.succeeded".equals(event.getType())) {
            String paymentIntentId = ((PaymentIntent) event.getDataObjectDeserializer().getObject().get()).getId();

            PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);
            Map<String, String> metadataMap = paymentIntent.getMetadata();


            String userId = metadataMap.get("userId");
            System.out.println(userId + "userID");


            User user = authService.getUserDetails(userId);


            cartService.deleteCartItemByWebHook(user);


        }

    }



}