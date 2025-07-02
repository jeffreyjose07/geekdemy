package com.example.geektrust.model;

public class Bill {
    private final float subTotal;
    private final Coupon coupon;
    private final float couponDiscount;
    private final float proDiscount;
    private final float membershipFee;
    private final float enrollmentFee;
    private final float total;

    public Bill(float subTotal, Coupon coupon, float couponDiscount, float proDiscount,
                float membershipFee, float enrollmentFee, float total) {
        this.subTotal = subTotal;
        this.coupon = coupon;
        this.couponDiscount = couponDiscount;
        this.proDiscount = proDiscount;
        this.membershipFee = membershipFee;
        this.enrollmentFee = enrollmentFee;
        this.total = total;
    }

    public float getSubTotal() { return subTotal; }
    public Coupon getCoupon() { return coupon; }
    public float getCouponDiscount() { return couponDiscount; }
    public float getProDiscount() { return proDiscount; }
    public float getMembershipFee() { return membershipFee; }
    public float getEnrollmentFee() { return enrollmentFee; }
    public float getTotal() { return total; }
}
