package com.yash.project.HotelManagementApp.service;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.checkout.Session;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import com.yash.project.HotelManagementApp.entity.Booking;
import com.yash.project.HotelManagementApp.entity.User;
import com.yash.project.HotelManagementApp.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
@Slf4j
public class CheckoutServiceImpl implements CheckoutService{

    private final BookingRepository bookingRepository;
    @Override
    public String getCheckoutSession(Booking booking, String successUrl, String failureUrl) {

        log.info("Creating session for booking id: "+ booking.getId());

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        try {

            CustomerCreateParams customerParams =  CustomerCreateParams.builder()
                            .setName(user.getName())
                            .setEmail(user.getEmail())
                            .build();
            Customer customer = Customer.create(customerParams);

            SessionCreateParams sessionParams = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setBillingAddressCollection(SessionCreateParams.BillingAddressCollection.REQUIRED)
                    .setCustomer(customer.getId())
                    .setSuccessUrl(successUrl)
                    .setCancelUrl(failureUrl)
                    .addLineItem(
                            SessionCreateParams.LineItem.builder()
                                    .setQuantity(1L)
                                    .setPriceData(
                                            SessionCreateParams.LineItem.PriceData.builder()
                                                    .setCurrency("inr")
                                                    .setUnitAmountDecimal(booking.getAmount().multiply(BigDecimal.valueOf(100)))
                                                    .setProductData(
                                                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                    .setName(booking.getHotel().getName()+" : "+booking.getRoom().getType())
                                                                    .setDescription("Booking ID: "+booking.getId())
                                                                    .build()
                                                    )
                                                    .build()
                                    )
                                    .build()
                    )
                    .build();

            Session session = Session.create(sessionParams);
            booking.setPaymentSessionId(session.getId());

            bookingRepository.save(booking);

            log.info("Created session successfully for booking with ID: {}", booking.getId());
            return session.getUrl();

        }catch (StripeException e){
            log.error("Stripe API error while creating session for booking {}: {}", booking.getId(), e.getMessage(), e);
            throw new RuntimeException("Payment session creation failed: " + e.getMessage());
        }
    }
}
