package com.example.geektrust.service;

import com.example.geektrust.Constants;

import java.math.BigDecimal;

/**
 * Calculates the enrollment fee based on the current subtotal.
 */
public class EnrollmentFeeCalculator {
    /**
     * Computes the enrollment fee. A fee is added when the subtotal is
     * below the configured threshold.
     */
    public BigDecimal compute(BigDecimal subTotal) {
        return subTotal.compareTo(Constants.ENROLLMENT_THRESHOLD) < 0
                ? Constants.ENROLLMENT_FEE
                : BigDecimal.ZERO;
    }
}
