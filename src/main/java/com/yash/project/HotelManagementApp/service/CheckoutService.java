package com.yash.project.HotelManagementApp.service;

import com.yash.project.HotelManagementApp.entity.Booking;

public interface CheckoutService {
    String getCheckoutSession(Booking booking, String successUrl, String failureUrl);
}
