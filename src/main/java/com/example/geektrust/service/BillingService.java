package com.example.geektrust.service;

import com.example.geektrust.model.*;

public class BillingService {
    private static final float PRO_MEMBERSHIP_FEE = 200f;
    private static final float ENROLLMENT_FEE = 500f;
    private static final float ENROLLMENT_THRESHOLD = 6666f;

    public Bill generateBill(Order order) {
        float subTotal = calculateSubTotal(order);
        float proDiscount = calculateProDiscount(order);
        Coupon coupon = determineCoupon(order, subTotal);
        float couponDiscount = coupon.discountAmount(order, subTotal);
        float membershipFee = calculateMembershipFee(order);

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

    private float calculateProDiscount(Order order) {
        if (!order.hasProMembership()) {
            return 0f;
        }
        return (float) order.getItems().stream()
                .mapToDouble(i -> i.getProgram().getPrice() * i.getQuantity() * i.getProgram().getProDiscountPercent() / 100.0)
                .sum();
    }

    private Coupon determineCoupon(Order order, float subTotal) {
        if (Coupon.B4G1.isApplicable(order, subTotal)) {
            return Coupon.B4G1;
        }
        if (Coupon.DEAL_G20.isApplicable(order, subTotal)) {
            return Coupon.DEAL_G20;
        }
        if (Coupon.DEAL_G5.isApplicable(order, subTotal)) {
            return Coupon.DEAL_G5;
        }
        return Coupon.NONE;
    }

    private float calculateMembershipFee(Order order) {
        return order.hasProMembership() ? PRO_MEMBERSHIP_FEE : 0f;
    }

    private float calculateEnrollmentFee(float total) {
        return total < ENROLLMENT_THRESHOLD ? ENROLLMENT_FEE : 0f;
    }
}
