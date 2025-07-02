package com.example.geektrust.service;

import com.example.geektrust.model.Bill;

public class BillPrinter {
    public void print(Bill bill) {
        System.out.printf("SUB_TOTAL %.2f%n", bill.getSubTotal());
        System.out.printf("COUPON_DISCOUNT %s %.2f%n", bill.getCoupon(), bill.getCouponDiscount());
        System.out.printf("TOTAL_PRO_DISCOUNT %.2f%n", bill.getProDiscount());
        System.out.printf("PRO_MEMBERSHIP_FEE %.2f%n", bill.getMembershipFee());
        System.out.printf("ENROLLMENT_FEE %.2f%n", bill.getEnrollmentFee());
        System.out.printf("TOTAL %.2f%n", bill.getTotal());
    }
}
