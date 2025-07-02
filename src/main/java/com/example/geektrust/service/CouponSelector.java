package com.example.geektrust.service;

import com.example.geektrust.Constants;
import com.example.geektrust.model.Coupon;
import com.example.geektrust.model.Order;

import java.math.BigDecimal;
import java.util.Comparator;

/**
 * Encapsulates the logic for selecting the most appropriate coupon
 * for a given order.
 */
public class CouponSelector {
    /**
     * Selects the coupon that should be applied to the order.
     * B4G1 is given priority when the quantity condition is met.
     */
    public Coupon selectCoupon(Order order, BigDecimal subTotal) {
        if (order.getTotalQuantity() >= Constants.B4G1_MIN_PROGRAMS) {
            return Coupon.B4G1;
        }
        return determineBestCoupon(order, subTotal);
    }

    /**
     * Determines the best coupon among the coupons present in the order
     * excluding B4G1.
     */
    Coupon determineBestCoupon(Order order, BigDecimal subTotal) {
        return order.getCoupons().stream()
                .filter(coupon -> coupon != Coupon.B4G1)
                .filter(coupon -> coupon.isApplicable(order, subTotal))
                .max(Comparator.comparing(c -> c.discountAmount(order, subTotal)))
                .orElse(Coupon.NONE);
    }
}
