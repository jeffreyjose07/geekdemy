package com.example.geektrust.model;

import java.math.BigDecimal;

/**
 * Represents the different programs offered by Geekdemy along with their base
 * price and the discount percentage provided to PRO members.
 */
public enum ProgramType {
    CERTIFICATION(new BigDecimal("3000.00"), 2),
    DEGREE(new BigDecimal("5000.00"), 3),
    DIPLOMA(new BigDecimal("2500.00"), 1);

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
