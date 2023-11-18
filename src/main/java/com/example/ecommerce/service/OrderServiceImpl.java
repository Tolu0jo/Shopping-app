package com.example.ecommerce.service;

import com.example.ecommerce.dto.CartDto;
import com.example.ecommerce.dto.CheckOutDto;
import com.example.ecommerce.dto.StripeResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;
    @Override
    public StripeResponse createSession(List<CartDto> cartDtos) throws StripeException {

        String successUrl= "http://127.0.0:3000/sucess";
        String failureUrl ="http://127.0.0:3000/fail";
        Stripe.apiKey =stripeSecretKey;

        List<SessionCreateParams.LineItem> sessionItemList = new ArrayList<>();
        for(CartDto cartDto:cartDtos){
            sessionItemList.add(createSessionLineItem(cartDto));
        }
        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl(failureUrl)
                .setSuccessUrl(successUrl)
                .addAllLineItem(sessionItemList)
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
             .setUnitAmount((long)cartDto.getUnitPrice()*100)
             .setProductData(
                     SessionCreateParams.LineItem.PriceData.ProductData.builder()
                             .setName(cartDto.getProduct().getProductName())
                             .build()
             ).build();

    }



}
