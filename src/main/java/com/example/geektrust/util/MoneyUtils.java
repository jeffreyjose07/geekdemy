package com.example.geektrust.util;

import com.example.geektrust.Constants;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class MoneyUtils {
    private MoneyUtils() { }

    public static BigDecimal scale(BigDecimal value) {
        if (value == null) {
            return BigDecimal.ZERO.setScale(Constants.MONEY_SCALE, RoundingMode.HALF_UP);
        }
        return value.setScale(Constants.MONEY_SCALE, RoundingMode.HALF_UP);
    }
}
