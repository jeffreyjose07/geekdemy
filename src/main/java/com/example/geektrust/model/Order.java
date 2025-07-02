package com.example.geektrust.model;

import com.example.geektrust.Constants;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Order {
    private final List<OrderItem> items = new ArrayList<>();
    private Membership membership = new NoMembership();
    private final List<Coupon> coupons = new ArrayList<>();

    public void addProgram(ProgramType program, int quantity) {
        if (program == null || quantity <= 0) {
            throw new IllegalArgumentException("Program cannot be null and quantity must be positive");
        }
        items.add(new OrderItem(program, quantity));
    }

    public void addProMembership() {
        this.membership = new ProMembership();
    }

    public boolean hasProMembership() {
        return membership instanceof ProMembership;
    }

    public void addCoupon(String code) {
        if (code == null) {
            return;
        }
        try {
            Coupon coupon = Coupon.valueOf(code.trim().toUpperCase());
            if (!coupons.contains(coupon)) {
                coupons.add(coupon);
            }
        } catch (IllegalArgumentException e) {
            // ignore unknown coupon
        }
    }

    public BigDecimal membershipFee() {
        return hasProMembership() ? Constants.PRO_MEMBERSHIP_FEE : BigDecimal.ZERO;
    }

    public BigDecimal calculateProDiscount() {
        return items.stream()
                .map(item -> {
                    BigDecimal discountPercent = new BigDecimal(item.getProgram().getProDiscountPercent())
                            .divide(new BigDecimal("100"));
                    return item.getProgram().getPrice()
                            .multiply(discountPercent)
                            .multiply(new BigDecimal(item.getQuantity()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<OrderItem> getItems() {
        return new ArrayList<>(items); // Return a copy to maintain encapsulation
    }

    public boolean hasCoupon(Coupon coupon) {
        return coupons.contains(coupon);
    }

    public int getTotalQuantity() {
        return items.stream()
                .mapToInt(OrderItem::getQuantity)
                .sum();
    }

    public BigDecimal getCheapestProgramPrice() {
        return items.stream()
                .map(item -> item.getProgram().getPrice())
                .min(Comparator.naturalOrder())
                .orElse(BigDecimal.ZERO);
    }
    
    public BigDecimal calculateSubTotal() {
        return items.stream()
                .map(item -> item.getProgram().getPrice()
                        .multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Coupon> getCoupons() { return new ArrayList<>(coupons); }
}
