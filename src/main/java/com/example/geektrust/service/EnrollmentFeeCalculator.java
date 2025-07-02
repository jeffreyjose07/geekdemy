package com.example.geektrust.service;

import java.math.BigDecimal;

/**
 * Calculates the enrollment fee based on the current subtotal.
 */
public class EnrollmentFeeCalculator {
    private static final BigDecimal ENROLLMENT_FEE = new BigDecimal("500.00");
    private static final BigDecimal ENROLLMENT_THRESHOLD = new BigDecimal("6666.00");
    /**
     * Computes the enrollment fee. A fee is added when the subtotal is
     * below the configured threshold.
     */
    public BigDecimal compute(BigDecimal subTotal) {
        return subTotal.compareTo(ENROLLMENT_THRESHOLD) < 0
                ? ENROLLMENT_FEE
                : BigDecimal.ZERO;
    }
}
