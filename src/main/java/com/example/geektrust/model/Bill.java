package com.example.geektrust.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Bill {
    private final BigDecimal subTotal;
    private final Coupon coupon;
    private final BigDecimal couponDiscount;
    private final BigDecimal proDiscount;
    private final BigDecimal membershipFee;
    private final BigDecimal enrollmentFee;
    private final BigDecimal total;

    public Bill(BigDecimal subTotal, Coupon coupon, BigDecimal couponDiscount, 
                BigDecimal proDiscount, BigDecimal membershipFee, 
                BigDecimal enrollmentFee, BigDecimal total) {
        this.subTotal = subTotal;
        this.coupon = coupon != null ? coupon : Coupon.NONE;
        this.couponDiscount = couponDiscount != null ? couponDiscount : BigDecimal.ZERO;
        this.proDiscount = proDiscount != null ? proDiscount : BigDecimal.ZERO;
        this.membershipFee = membershipFee != null ? membershipFee : BigDecimal.ZERO;
        this.enrollmentFee = enrollmentFee != null ? enrollmentFee : BigDecimal.ZERO;
        this.total = total != null ? total : BigDecimal.ZERO;
    }

    public BigDecimal getSubTotal() { 
        return subTotal != null ? subTotal : BigDecimal.ZERO; 
    }
    
    public Coupon getCoupon() { 
        return coupon != null ? coupon : Coupon.NONE; 
    }
    
    public BigDecimal getCouponDiscount() { 
        return couponDiscount != null ? couponDiscount : BigDecimal.ZERO; 
    }
    
    public BigDecimal getProDiscount() { 
        return proDiscount != null ? proDiscount : BigDecimal.ZERO; 
    }
    
    public BigDecimal getMembershipFee() { 
        return membershipFee != null ? membershipFee : BigDecimal.ZERO; 
    }
    
    public BigDecimal getEnrollmentFee() { 
        return enrollmentFee != null ? enrollmentFee : BigDecimal.ZERO; 
    }
    
    public BigDecimal getTotal() { 
        return total != null ? total : BigDecimal.ZERO; 
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return Objects.equals(subTotal, bill.subTotal) &&
               coupon == bill.coupon &&
               Objects.equals(couponDiscount, bill.couponDiscount) &&
               Objects.equals(proDiscount, bill.proDiscount) &&
               Objects.equals(membershipFee, bill.membershipFee) &&
               Objects.equals(enrollmentFee, bill.enrollmentFee) &&
               Objects.equals(total, bill.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subTotal, coupon, couponDiscount, proDiscount, membershipFee, enrollmentFee, total);
    }
}
