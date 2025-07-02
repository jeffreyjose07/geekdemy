package com.example.geektrust.service;

import com.example.geektrust.model.Bill;

import java.math.RoundingMode;

public class BillPrinter {
    public void print(Bill bill) {
        if (bill == null) {
            throw new IllegalArgumentException("Bill cannot be null");
        }
        
        // Format all values to 2 decimal places
        System.out.printf("SUB_TOTAL %s%n", formatCurrency(bill.getSubTotal()));
        System.out.printf("COUPON_DISCOUNT %s %s%n", 
                bill.getCoupon(), 
                formatCurrency(bill.getCouponDiscount()));
        System.out.printf("TOTAL_PRO_DISCOUNT %s%n", formatCurrency(bill.getProDiscount()));
        System.out.printf("PRO_MEMBERSHIP_FEE %s%n", formatCurrency(bill.getMembershipFee()));
        System.out.printf("ENROLLMENT_FEE %s%n", formatCurrency(bill.getEnrollmentFee()));
        System.out.printf("TOTAL %s%n", formatCurrency(bill.getTotal()));
    }
    
    private String formatCurrency(Number amount) {
        if (amount == null) {
            return "0.00";
        }
        if (amount instanceof java.math.BigDecimal) {
            return ((java.math.BigDecimal) amount).setScale(2, RoundingMode.HALF_UP).toString();
        }
        return String.format("%.2f", amount);
    }
}
