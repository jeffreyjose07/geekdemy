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

        @Override
        public BigDecimal discountAmount(Order order, BigDecimal subTotal) {
            return order != null ? order.getCheapestProgramPriceAfterDiscount() : BigDecimal.ZERO;
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
                   subTotal.compareTo(DEAL_G20_THRESHOLD) >= 0;
        }

        @Override
        public BigDecimal discountAmount(Order order, BigDecimal subTotal) {
            return percentageDiscount(subTotal, DEAL_G20_DISCOUNT_RATE);
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
                   order.getTotalQuantity() >= DEAL_G5_MIN_PROGRAMS;
        }

        @Override
        public BigDecimal discountAmount(Order order, BigDecimal subTotal) {
            return percentageDiscount(subTotal, DEAL_G5_DISCOUNT_RATE);
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

        @Override
        public BigDecimal discountAmount(Order order, BigDecimal subTotal) {
            return BigDecimal.ZERO;
        }
    };

    private static final BigDecimal DEAL_G20_THRESHOLD = new BigDecimal("10000.00");
    private static final BigDecimal DEAL_G20_DISCOUNT_RATE = new BigDecimal("0.20");
    private static final BigDecimal DEAL_G5_DISCOUNT_RATE = new BigDecimal("0.05");
    private static final int DEAL_G5_MIN_PROGRAMS = 2;

    /**
     * Checks if this coupon is applicable to the given order and subtotal.
     *
     * @param order the order to check
     * @param subTotal the subtotal of the order
     * @return true if the coupon is applicable, false otherwise
     */
    public abstract boolean isApplicable(Order order, BigDecimal subTotal);

    /**
     * Calculates the discount amount provided by this coupon.
     *
     * @param order the order to calculate the discount for
     * @param subTotal the subtotal of the order
     * @return the discount amount
     */
    public abstract BigDecimal discountAmount(Order order, BigDecimal subTotal);

    private static BigDecimal percentageDiscount(BigDecimal subTotal, BigDecimal rate) {
        if (subTotal == null || rate == null) {
            return BigDecimal.ZERO;
        }
        return subTotal.multiply(rate);
    }

    /**
     * Converts a string code to the corresponding {@link Coupon} instance.
     *
     * @param code the coupon code, case-insensitive
     * @return the matching {@link Coupon} or {@code null} if no match is found
     */
    public static Coupon fromCode(String code) {
        if (code == null) {
            return null;
        }
        return java.util.Arrays.stream(values())
                .filter(c -> c.name().equalsIgnoreCase(code.trim()))
                .findFirst()
                .orElse(null);
    }
}
