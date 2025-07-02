package com.example.geektrust;

import com.example.geektrust.util.MoneyUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyUtilsTest {
    @Test
    void scaleRoundsHalfUp() {
        BigDecimal value = new BigDecimal("123.456");
        assertEquals(new BigDecimal("123.46"), MoneyUtils.scale(value));
    }

    @Test
    void scaleHandlesNullAsZero() {
        assertEquals(new BigDecimal("0.00"), MoneyUtils.scale(null));
    }
}
