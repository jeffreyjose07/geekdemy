package com.example.geektrust.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** Utility methods for working with monetary values. */
public final class MoneyUtils {
    private static final int MONEY_SCALE = 2;

    private MoneyUtils() { }

    public static BigDecimal scale(BigDecimal value) {
        if (value == null) {
            return BigDecimal.ZERO.setScale(MONEY_SCALE, RoundingMode.HALF_UP);
        }
        return value.setScale(MONEY_SCALE, RoundingMode.HALF_UP);
    }
}
