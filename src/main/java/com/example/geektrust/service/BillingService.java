package com.example.geektrust.service;

import com.example.geektrust.Constants;
import com.example.geektrust.model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BillingService {
    
    public Bill generateBill(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        
        BigDecimal subTotal = order.calculateSubTotal();
        
        // Check if B4G1 is applicable (4 or more programs)
        boolean isB4G1Applicable = order.getTotalQuantity() >= Constants.B4G1_MIN_PROGRAMS;
        
        // Determine which coupon to use
        Coupon coupon = isB4G1Applicable ? 
                       Coupon.B4G1 : 
                       determineBestCoupon(order, subTotal);
        
        // Calculate coupon discount using coupon behavior
        BigDecimal couponDiscount = coupon.discountAmount(order, subTotal);
        
        // Calculate amount after coupon discount
        BigDecimal amountAfterCoupon = subTotal.subtract(couponDiscount);
        
        // Calculate pro discount based on program-specific rates
        BigDecimal proDiscount = order.hasProMembership() ? order.calculateProDiscount() : BigDecimal.ZERO;
        
        // Calculate membership fee
        BigDecimal membershipFee = order.membershipFee();
        
        // Calculate total before enrollment fee
        BigDecimal totalBeforeEnrollment = amountAfterCoupon
                .subtract(proDiscount)
                .add(membershipFee);
        
        // Calculate enrollment fee (only if subtotal is less than threshold)
        BigDecimal enrollmentFee = subTotal.compareTo(Constants.ENROLLMENT_THRESHOLD) < 0 ? 
                                 Constants.ENROLLMENT_FEE : 
                                 BigDecimal.ZERO;
        
        // Calculate final total
        BigDecimal total = totalBeforeEnrollment.add(enrollmentFee);
        
        return new Bill(
            subTotal.setScale(2, RoundingMode.HALF_UP),
            coupon,
            couponDiscount.setScale(2, RoundingMode.HALF_UP),
            proDiscount.setScale(2, RoundingMode.HALF_UP),
            membershipFee.setScale(2, RoundingMode.HALF_UP),
            enrollmentFee.setScale(2, RoundingMode.HALF_UP),
            total.setScale(2, RoundingMode.HALF_UP)
        );
    }
    
    private Coupon determineBestCoupon(Order order, BigDecimal subTotal) {
        // Check other coupons in the order they were added
        return order.getCoupons().stream()
                .filter(coupon -> coupon != Coupon.B4G1) // Skip B4G1 as it's handled in generateBill
                .filter(coupon -> coupon.isApplicable(order, subTotal))
                .findFirst()
                .orElse(Coupon.NONE);
    }
    
    private BigDecimal calculateEnrollmentFee(BigDecimal totalBeforeEnrollment) {
        if (totalBeforeEnrollment.compareTo(Constants.ENROLLMENT_THRESHOLD) < 0) {
            return Constants.ENROLLMENT_FEE;
        }
        return BigDecimal.ZERO;
    }
}
