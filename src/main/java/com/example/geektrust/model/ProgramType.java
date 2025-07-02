package com.example.geektrust.model;

import java.math.BigDecimal;

/**
 * Represents the different programs offered by Geekdemy along with their base
 * price and the discount percentage provided to PRO members.
 */
public enum ProgramType {
    CERTIFICATION(CERTIFICATION_PRICE, CERTIFICATION_PRO_DISCOUNT_PERCENT),
    DEGREE(DEGREE_PRICE, DEGREE_PRO_DISCOUNT_PERCENT),
    DIPLOMA(DIPLOMA_PRICE, DIPLOMA_PRO_DISCOUNT_PERCENT);

    private static final BigDecimal CERTIFICATION_PRICE = new BigDecimal("3000.00");
    private static final BigDecimal DEGREE_PRICE = new BigDecimal("5000.00");
    private static final BigDecimal DIPLOMA_PRICE = new BigDecimal("2500.00");

    private static final int CERTIFICATION_PRO_DISCOUNT_PERCENT = 2;
    private static final int DEGREE_PRO_DISCOUNT_PERCENT = 3;
    private static final int DIPLOMA_PRO_DISCOUNT_PERCENT = 1;

    private final BigDecimal price;
    private final int proDiscountPercent;

    ProgramType(BigDecimal price, int proDiscountPercent) {
        this.price = price;
        this.proDiscountPercent = proDiscountPercent;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getProDiscountPercent() {
        return proDiscountPercent;
    }
}
