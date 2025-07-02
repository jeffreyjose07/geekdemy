package com.example.geektrust.model;

public class NoMembership implements Membership {
    @Override
    public float discountFor(OrderItem item) {
        return 0f;
    }

    @Override
    public float fee() {
        return 0f;
    }
}
