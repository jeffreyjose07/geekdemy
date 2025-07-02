package com.example.geektrust.service;

import com.example.geektrust.Constants;
import com.example.geektrust.model.*;

import java.math.BigDecimal;
import com.example.geektrust.util.MoneyUtils;

public class BillingService {
    
    public Bill generateBill(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        
        BigDecimal baseTotal = order.calculateSubTotal();

        BigDecimal proDiscount = computeProDiscount(order);
        BigDecimal membershipFee = order.membershipFee();

        BigDecimal subTotal = baseTotal.subtract(proDiscount).add(membershipFee);

        Coupon coupon = selectCoupon(order, subTotal);
        BigDecimal couponDiscount = coupon.discountAmount(order, subTotal);

        BigDecimal totalBeforeEnrollment = subTotal.subtract(couponDiscount);

        BigDecimal enrollmentFee = computeEnrollmentFee(subTotal);

        BigDecimal total = totalBeforeEnrollment.add(enrollmentFee);

        return new Bill(
            MoneyUtils.scale(subTotal),
            coupon,
            MoneyUtils.scale(couponDiscount),
            MoneyUtils.scale(proDiscount),
            MoneyUtils.scale(membershipFee),
            MoneyUtils.scale(enrollmentFee),
            MoneyUtils.scale(total)
        );
    }

    private Coupon selectCoupon(Order order, BigDecimal subTotal) {
        if (order.getTotalQuantity() >= Constants.B4G1_MIN_PROGRAMS) {
            return Coupon.B4G1;
        }
        return determineBestCoupon(order, subTotal);
    }

    private BigDecimal computeProDiscount(Order order) {
        return order.calculateProDiscount();
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
