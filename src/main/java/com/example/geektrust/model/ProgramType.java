package com.example.geektrust.model;

public enum ProgramType {
    CERTIFICATION(3000, 2),
    DEGREE(5000, 3),
    DIPLOMA(2500, 1);

    private final int price;
    private final int proDiscountPercent;

    ProgramType(int price, int proDiscountPercent) {
        this.price = price;
        this.proDiscountPercent = proDiscountPercent;
    }

    public int getPrice() {
        return price;
    }

    public int getProDiscountPercent() {
        return proDiscountPercent;
    }
}
