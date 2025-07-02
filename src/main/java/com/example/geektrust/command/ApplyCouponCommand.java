package com.example.geektrust.command;

import com.example.geektrust.model.Order;
import com.example.geektrust.service.BillingService;

public class ApplyCouponCommand implements Command {
    private final String coupon;

    public ApplyCouponCommand(String coupon) {
        this.coupon = coupon;
    }

    @Override
    public void execute(Order order, BillingService billingService) {
        order.addCoupon(coupon);
    }
}
