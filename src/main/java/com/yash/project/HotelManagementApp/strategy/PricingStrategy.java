package com.yash.project.HotelManagementApp.strategy;

import com.yash.project.HotelManagementApp.entity.Inventory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal calculatePrice(Inventory inventory);
}
