package com.example.geektrust.model;

public interface Membership {
    float discountFor(OrderItem item);
    float fee();
}
