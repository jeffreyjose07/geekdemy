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
        Coupon coupon = Coupon.fromCode(code);
        if (coupon != null && !coupons.contains(coupon)) {
            coupons.add(coupon);
        }
    }

    public BigDecimal membershipFee() {
        return hasProMembership() ? Constants.PRO_MEMBERSHIP_FEE : BigDecimal.ZERO;
    }

    public BigDecimal calculateProDiscount() {
        return items.stream()
                .map(membership::calculateDiscount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Returns the lowest program price after applying membership discount on a
     * single unit of each program.
     */
    public BigDecimal getCheapestProgramPriceAfterDiscount() {
        return items.stream()
                .map(item -> {
                    BigDecimal basePrice = item.getProgram().getPrice();
                    BigDecimal discount = membership.calculateDiscount(new OrderItem(item.getProgram(), 1));
                    return basePrice.subtract(discount);
                })
                .min(Comparator.naturalOrder())
                .orElse(BigDecimal.ZERO);
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
