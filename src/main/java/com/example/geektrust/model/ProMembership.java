package com.example.geektrust.model;

import com.example.geektrust.Constants;

import java.math.BigDecimal;
import java.math.RoundingMode;
import com.example.geektrust.util.MoneyUtils;

/**
 * Implementation of {@link Membership} for users who opt for PRO membership.
 */
public class ProMembership implements Membership {
    private static final BigDecimal ONE_HUNDRED = new BigDecimal("100");
    private static final int DISCOUNT_SCALE = 4;
    @Override
    public BigDecimal calculateDiscount(OrderItem item) {
        ProgramType program = item.getProgram();
        BigDecimal discountPercent = new BigDecimal(program.getProDiscountPercent())
                .divide(ONE_HUNDRED, DISCOUNT_SCALE, RoundingMode.HALF_UP);
        return MoneyUtils.scale(
                program.getPrice()
                        .multiply(discountPercent)
                        .multiply(new BigDecimal(item.getQuantity())));
    }

    @Override
    public BigDecimal getFee() {
        return Constants.PRO_MEMBERSHIP_FEE;
    }
}
