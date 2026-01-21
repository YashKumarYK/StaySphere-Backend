package com.yash.project.HotelManagementApp.strategy;

import com.yash.project.HotelManagementApp.entity.Inventory;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;


@RequiredArgsConstructor
public class SurgePricingStrategy implements PricingStrategy{

    private final PricingStrategy wrapped;
    @Override
    public BigDecimal calculatePrice(Inventory inventory) {


        return wrapped.calculatePrice(inventory).multiply(inventory.getSurgeFactor());
    }
}
