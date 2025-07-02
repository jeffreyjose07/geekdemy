package com.example.geektrust;

import java.math.BigDecimal;

public final class Constants {
    // Membership fees
    public static final BigDecimal PRO_MEMBERSHIP_FEE = new BigDecimal("200.00");
    public static final BigDecimal ENROLLMENT_FEE = new BigDecimal("500.00");
    public static final BigDecimal ENROLLMENT_THRESHOLD = new BigDecimal("6666.00");
    
    // Coupon thresholds and rates
    public static final BigDecimal DEAL_G20_THRESHOLD = new BigDecimal("10000.00");
    public static final BigDecimal DEAL_G20_DISCOUNT_RATE = new BigDecimal("0.20");
    public static final BigDecimal DEAL_G5_DISCOUNT_RATE = new BigDecimal("0.05");
    public static final int DEAL_G5_MIN_PROGRAMS = 2;
    public static final int B4G1_MIN_PROGRAMS = 4;
    
    // Program prices and discounts
    public static final BigDecimal CERTIFICATION_PRICE = new BigDecimal("3000.00");
    public static final BigDecimal DEGREE_PRICE = new BigDecimal("5000.00");
    public static final BigDecimal DIPLOMA_PRICE = new BigDecimal("2500.00");
    public static final int CERTIFICATION_PRO_DISCOUNT_PERCENT = 2;
    public static final int DEGREE_PRO_DISCOUNT_PERCENT = 3;
    public static final int DIPLOMA_PRO_DISCOUNT_PERCENT = 1;

    // Common constants
    public static final BigDecimal ONE_HUNDRED = new BigDecimal("100");
    public static final int MONEY_SCALE = 2;
    public static final int DISCOUNT_SCALE = 4;
    
    private Constants() {
        // Prevent instantiation
    }
}
