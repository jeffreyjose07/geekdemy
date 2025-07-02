package com.example.geektrust.service;

import com.example.geektrust.model.*;

import java.math.BigDecimal;
import com.example.geektrust.util.MoneyUtils;

/**
 * Service responsible for generating the final {@link Bill} for an order.
 * The class now delegates coupon selection and enrollment fee calculation
 * to dedicated components to keep this class focused on orchestration.
 */
public class BillingService {
    private final CouponSelector couponSelector;
    private final EnrollmentFeeCalculator enrollmentFeeCalculator;

    public BillingService() {
        this(new CouponSelector(), new EnrollmentFeeCalculator());
    }

    BillingService(CouponSelector couponSelector, EnrollmentFeeCalculator enrollmentFeeCalculator) {
        this.couponSelector = couponSelector;
        this.enrollmentFeeCalculator = enrollmentFeeCalculator;
    }
    
    public Bill generateBill(Order order) {
        validate(order);

        BigDecimal proDiscount = order.calculateProDiscount();
        BigDecimal membershipFee = order.membershipFee();
        BigDecimal subTotal = calculateSubTotal(order.calculateSubTotal(), proDiscount, membershipFee);

        Coupon coupon = couponSelector.selectCoupon(order, subTotal);
        BigDecimal couponDiscount = coupon.discountAmount(order, subTotal);
        BigDecimal enrollmentFee = enrollmentFeeCalculator.compute(subTotal);
        BigDecimal total = subTotal.subtract(couponDiscount).add(enrollmentFee);

        return Bill.builder()
                .subTotal(MoneyUtils.scale(subTotal))
                .coupon(coupon)
                .couponDiscount(MoneyUtils.scale(couponDiscount))
                .proDiscount(MoneyUtils.scale(proDiscount))
                .membershipFee(MoneyUtils.scale(membershipFee))
                .enrollmentFee(MoneyUtils.scale(enrollmentFee))
                .total(MoneyUtils.scale(total))
                .build();
    }

    private void validate(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
    }

    private BigDecimal calculateSubTotal(BigDecimal baseTotal, BigDecimal proDiscount, BigDecimal membershipFee) {
        return baseTotal.subtract(proDiscount).add(membershipFee);
    }


}
