package com.example.geektrust.service;

import com.example.geektrust.Constants;
import com.example.geektrust.model.*;

import java.math.BigDecimal;
import com.example.geektrust.util.MoneyUtils;

public class BillingService {
    
    public Bill generateBill(Order order) {
        validate(order);

        BigDecimal proDiscount = order.calculateProDiscount();
        BigDecimal membershipFee = order.membershipFee();
        BigDecimal subTotal = calculateSubTotal(order.calculateSubTotal(), proDiscount, membershipFee);

        Coupon coupon = selectCoupon(order, subTotal);
        BigDecimal couponDiscount = coupon.discountAmount(order, subTotal);
        BigDecimal enrollmentFee = computeEnrollmentFee(subTotal);
        BigDecimal total = subTotal.subtract(couponDiscount).add(enrollmentFee);

        return Bill.builder()
                .subTotal(MoneyUtils.scale(subTotal))
                .coupon(coupon)
                .couponDiscount(MoneyUtils.scale(couponDiscount))
                .proDiscount(MoneyUtils.scale(proDiscount))
                .membershipFee(MoneyUtils.scale(membershipFee))
                .enrollmentFee(MoneyUtils.scale(enrollmentFee))
                .total(MoneyUtils.scale(total))
                .build();
    }

    private void validate(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
    }

    private BigDecimal calculateSubTotal(BigDecimal baseTotal, BigDecimal proDiscount, BigDecimal membershipFee) {
        return baseTotal.subtract(proDiscount).add(membershipFee);
    }


    private Coupon selectCoupon(Order order, BigDecimal subTotal) {
        if (order.getTotalQuantity() >= Constants.B4G1_MIN_PROGRAMS) {
            return Coupon.B4G1;
        }
        return determineBestCoupon(order, subTotal);
    }

    private BigDecimal computeEnrollmentFee(BigDecimal subTotal) {
        return subTotal.compareTo(Constants.ENROLLMENT_THRESHOLD) < 0
                ? Constants.ENROLLMENT_FEE
                : BigDecimal.ZERO;
    }

    private Coupon determineBestCoupon(Order order, BigDecimal subTotal) {
        return order.getCoupons().stream()
                .filter(coupon -> coupon != Coupon.B4G1)
                .filter(coupon -> coupon.isApplicable(order, subTotal))
                .max(java.util.Comparator.comparing(
                        c -> c.discountAmount(order, subTotal)))
                .orElse(Coupon.NONE);
    }
}
