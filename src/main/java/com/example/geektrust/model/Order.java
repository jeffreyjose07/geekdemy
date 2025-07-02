package com.example.geektrust.model;

import java.util.ArrayList;
import java.util.List;

import com.example.geektrust.model.Membership;
import com.example.geektrust.model.NoMembership;
import com.example.geektrust.model.ProMembership;

public class Order {
    private final List<OrderItem> items = new ArrayList<>();
    private Membership membership = new NoMembership();
    private final List<Coupon> coupons = new ArrayList<>();

    public void addProgram(ProgramType program, int quantity) {
        items.add(new OrderItem(program, quantity));
    }

    public void addProMembership() {
        this.membership = new ProMembership();
    }

    public boolean hasProMembership() {
        return membership instanceof ProMembership;
    }

    public void addCoupon(String code) {
        try {
            coupons.add(Coupon.valueOf(code));
        } catch (IllegalArgumentException e) {
            // ignore unknown coupon
        }
    }

    public float membershipFee() {
        return membership.fee();
    }

    public float calculateProDiscount() {
        return (float) items.stream()
                .mapToDouble(i -> membership.discountFor(i))
                .sum();
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
