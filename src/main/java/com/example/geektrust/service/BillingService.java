package com.example.geektrust.service;

import com.example.geektrust.model.*;

public class BillingService {
    private static final float ENROLLMENT_FEE = 500f;
    private static final float ENROLLMENT_THRESHOLD = 6666f;

    public Bill generateBill(Order order) {
        float subTotal = calculateSubTotal(order);
        float proDiscount = order.calculateProDiscount();
        Coupon coupon = determineCoupon(order, subTotal);
        float couponDiscount = coupon.discountAmount(order, subTotal);
        float membershipFee = order.membershipFee();

        float totalBeforeEnrollment = subTotal - proDiscount - couponDiscount + membershipFee;
        float enrollmentFee = calculateEnrollmentFee(totalBeforeEnrollment);
        float total = totalBeforeEnrollment + enrollmentFee;

        return new Bill(subTotal, coupon, couponDiscount, proDiscount, membershipFee, enrollmentFee, total);
    }

    private float calculateSubTotal(Order order) {
        return (float) order.getItems().stream()
                .mapToDouble(i -> i.getProgram().getPrice() * i.getQuantity())
                .sum();
    }

    private Coupon determineCoupon(Order order, float subTotal) {
        return java.util.stream.Stream.of(Coupon.B4G1, Coupon.DEAL_G20, Coupon.DEAL_G5)
                .filter(c -> c.isApplicable(order, subTotal))
                .findFirst()
                .orElse(Coupon.NONE);
    }

    private float calculateEnrollmentFee(float total) {
        return total < ENROLLMENT_THRESHOLD ? ENROLLMENT_FEE : 0f;
    }
}
