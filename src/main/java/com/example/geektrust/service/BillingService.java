package com.example.geektrust.service;

import com.example.geektrust.model.*;

public class BillingService {
    private static final float PRO_MEMBERSHIP_FEE = 200f;
    private static final float ENROLLMENT_FEE = 500f;
    private static final float ENROLLMENT_THRESHOLD = 6666f;
    private static final float DEAL_G20_THRESHOLD = 10000f;
    private static final int DEAL_G5_MIN_PROGRAMS = 2;
    private static final int B4G1_MIN_PROGRAMS = 4;

    public Bill generateBill(Order order) {
        float subTotal = calculateSubTotal(order);
        float proDiscount = calculateProDiscount(order);
        Coupon coupon = determineCoupon(order, subTotal);
        float couponDiscount = calculateCouponDiscount(order, subTotal, coupon);
        float membershipFee = order.hasProMembership() ? PRO_MEMBERSHIP_FEE : 0f;

        float total = subTotal - proDiscount - couponDiscount + membershipFee;
        float enrollmentFee = total < ENROLLMENT_THRESHOLD ? ENROLLMENT_FEE : 0f;
        total += enrollmentFee;

        return new Bill(subTotal, coupon, couponDiscount, proDiscount, membershipFee, enrollmentFee, total);
    }

    private float calculateSubTotal(Order order) {
        return (float) order.getItems().stream()
                .mapToDouble(i -> i.getProgram().getPrice() * i.getQuantity())
                .sum();
    }

    private float calculateProDiscount(Order order) {
        if (!order.hasProMembership()) {
            return 0f;
        }
        return (float) order.getItems().stream()
                .mapToDouble(i -> i.getProgram().getPrice() * i.getQuantity() * i.getProgram().getProDiscountPercent() / 100.0)
                .sum();
    }

    private Coupon determineCoupon(Order order, float subTotal) {
        if (order.getTotalQuantity() >= B4G1_MIN_PROGRAMS) {
            return Coupon.B4G1;
        }
        if (order.hasCoupon(Coupon.DEAL_G20) && subTotal >= DEAL_G20_THRESHOLD) {
            return Coupon.DEAL_G20;
        }
        if (order.hasCoupon(Coupon.DEAL_G5) && order.getTotalQuantity() >= DEAL_G5_MIN_PROGRAMS) {
            return Coupon.DEAL_G5;
        }
        return Coupon.NONE;
    }

    private float calculateCouponDiscount(Order order, float subTotal, Coupon coupon) {
        switch (coupon) {
            case B4G1:
                return cheapestProgramPrice(order);
            case DEAL_G20:
                return subTotal * 20f / 100f;
            case DEAL_G5:
                return subTotal * 5f / 100f;
            default:
                return 0f;
        }
    }

    private float cheapestProgramPrice(Order order) {
        return (float) order.getItems().stream()
                .mapToDouble(i -> i.getProgram().getPrice())
                .min()
                .orElse(0d);
    }
}
