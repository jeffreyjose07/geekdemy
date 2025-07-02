package com.example.geektrust.model;

import com.example.geektrust.Constants;

import java.math.BigDecimal;

/**
 * Represents different types of coupons that can be applied to an order.
 * Each coupon has specific rules for applicability and discount calculation.
 */
public enum Coupon {
    /**
     * Buy 4 Get 1 Free - Applies to the cheapest program when 4 or more programs are purchased.
     */
    B4G1 {
        @Override
        public boolean isApplicable(Order order, BigDecimal subTotal) {
            return order != null && order.getTotalQuantity() >= Constants.B4G1_MIN_PROGRAMS;
        }
    },
    
    /**
     * 20% discount - Applies when the subtotal is >= 10,000.
     */
    DEAL_G20 {
        @Override
        public boolean isApplicable(Order order, BigDecimal subTotal) {
            return order != null && 
                   order.hasCoupon(this) && 
                   subTotal != null && 
                   subTotal.compareTo(Constants.DEAL_G20_THRESHOLD) >= 0;
        }
    },
    
    /**
     * 5% discount - Applies when 2 or more programs are purchased.
     */
    DEAL_G5 {
        @Override
        public boolean isApplicable(Order order, BigDecimal subTotal) {
            return order != null && 
                   order.hasCoupon(this) && 
                   order.getTotalQuantity() >= Constants.DEAL_G5_MIN_PROGRAMS;
        }
    },
    
    /**
     * No coupon applied.
     */
    NONE {
        @Override
        public boolean isApplicable(Order order, BigDecimal subTotal) {
            return false;
        }
    };

    /**
     * Checks if this coupon is applicable to the given order and subtotal.
     *
     * @param order the order to check
     * @param subTotal the subtotal of the order
     * @return true if the coupon is applicable, false otherwise
     */
    public abstract boolean isApplicable(Order order, BigDecimal subTotal);
    
    /**
     * Gets the discount amount for this coupon based on the order and subtotal.
     * This is now handled by BillingService for better encapsulation.
     *
     * @param order the order to calculate discount for
     * @param subTotal the subtotal of the order
     * @return the discount amount (always zero, as calculation is handled by BillingService)
     * @deprecated Use BillingService.calculateCouponDiscount() instead
     */
    @Deprecated
    public BigDecimal discountAmount(Order order, BigDecimal subTotal) {
        return BigDecimal.ZERO;
    }
}
