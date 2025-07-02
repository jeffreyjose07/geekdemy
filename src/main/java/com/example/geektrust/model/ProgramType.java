package com.example.geektrust.model;

import com.example.geektrust.Constants;

import java.math.BigDecimal;

public enum ProgramType {
    CERTIFICATION(Constants.CERTIFICATION_PRICE, Constants.CERTIFICATION_PRO_DISCOUNT_PERCENT),
    DEGREE(Constants.DEGREE_PRICE, Constants.DEGREE_PRO_DISCOUNT_PERCENT),
    DIPLOMA(Constants.DIPLOMA_PRICE, Constants.DIPLOMA_PRO_DISCOUNT_PERCENT);

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
