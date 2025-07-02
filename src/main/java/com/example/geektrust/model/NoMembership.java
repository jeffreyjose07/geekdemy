package com.example.geektrust.model;

import java.math.BigDecimal;

public class NoMembership implements Membership {
    @Override
    public BigDecimal calculateDiscount(OrderItem item) {
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal getFee() {
        return BigDecimal.ZERO;
    }
}
