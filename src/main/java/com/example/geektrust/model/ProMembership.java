package com.example.geektrust.model;

import com.example.geektrust.Constants;

import java.math.BigDecimal;
import java.math.RoundingMode;
import com.example.geektrust.util.MoneyUtils;

public class ProMembership implements Membership {
    @Override
    public BigDecimal calculateDiscount(OrderItem item) {
        ProgramType program = item.getProgram();
        BigDecimal discountPercent = new BigDecimal(program.getProDiscountPercent())
                .divide(Constants.ONE_HUNDRED, Constants.DISCOUNT_SCALE, RoundingMode.HALF_UP);
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
