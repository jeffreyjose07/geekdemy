package com.example.geektrust.model;

public class ProMembership implements Membership {
    public static final float FEE = 200f;

    @Override
    public float discountFor(OrderItem item) {
        ProgramType program = item.getProgram();
        return program.getPrice() * item.getQuantity() * program.getProDiscountPercent() / 100f;
    }

    @Override
    public float fee() {
        return FEE;
    }
}
