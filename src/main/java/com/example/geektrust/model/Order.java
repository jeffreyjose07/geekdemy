package com.example.geektrust.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<OrderItem> items = new ArrayList<>();
    private boolean proMembership;
    private final List<Coupon> coupons = new ArrayList<>();

    public void addProgram(ProgramType program, int quantity) {
        items.add(new OrderItem(program, quantity));
    }

    public void addProMembership() {
        this.proMembership = true;
    }

    public boolean hasProMembership() {
        return proMembership;
    }

    public void addCoupon(String code) {
        try {
            coupons.add(Coupon.valueOf(code));
        } catch (IllegalArgumentException e) {
            // ignore unknown coupon
        }
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public boolean hasCoupon(Coupon coupon) {
        return coupons.contains(coupon);
    }

    public int getTotalQuantity() {
        return items.stream().mapToInt(OrderItem::getQuantity).sum();
    }

    public float getCheapestProgramPrice() {
        return (float) items.stream()
                .mapToDouble(i -> i.getProgram().getPrice())
                .min()
                .orElse(0d);
    }
}
