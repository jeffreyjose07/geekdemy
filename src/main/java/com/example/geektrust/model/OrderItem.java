package com.example.geektrust.model;

public class OrderItem {
    private final ProgramType program;
    private final int quantity;

    public OrderItem(ProgramType program, int quantity) {
        this.program = program;
        this.quantity = quantity;
    }

    public ProgramType getProgram() {
        return program;
    }

    public int getQuantity() {
        return quantity;
    }
}
