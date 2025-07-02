package com.example.geektrust;

import com.example.geektrust.service.EnrollmentFeeCalculator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnrollmentFeeCalculatorTest {
    private final EnrollmentFeeCalculator calculator = new EnrollmentFeeCalculator();

    @Test
    void chargesFeeWhenBelowThreshold() {
        BigDecimal fee = calculator.compute(new BigDecimal("5000"));
        assertEquals(new BigDecimal("500.00"), fee);
    }

    @Test
    void waivesFeeWhenAboveThreshold() {
        BigDecimal fee = calculator.compute(new BigDecimal("8000"));
        assertEquals(BigDecimal.ZERO.setScale(2), fee.setScale(2));
    }
}
