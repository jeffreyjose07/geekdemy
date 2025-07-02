package com.example.geektrust.model;

import com.example.geektrust.Constants;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProMembership implements Membership {
    @Override
    public BigDecimal calculateDiscount(OrderItem item) {
        ProgramType program = item.getProgram();
        BigDecimal discountPercent = new BigDecimal(program.getProDiscountPercent())
                .divide(new BigDecimal("100"), 4, RoundingMode.HALF_UP);
        return program.getPrice()
                .multiply(discountPercent)
                .multiply(new BigDecimal(item.getQuantity()))
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getFee() {
        return Constants.PRO_MEMBERSHIP_FEE;
    }
}
