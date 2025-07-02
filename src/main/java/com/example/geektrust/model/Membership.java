package com.example.geektrust.model;

import java.math.BigDecimal;

public interface Membership {
    BigDecimal calculateDiscount(OrderItem item);
    BigDecimal getFee();
}
