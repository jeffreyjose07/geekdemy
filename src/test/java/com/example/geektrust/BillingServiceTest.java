package com.example.geektrust;

import com.example.geektrust.model.Coupon;
import com.example.geektrust.model.Order;
import com.example.geektrust.model.ProgramType;
import com.example.geektrust.service.BillingService;
import com.example.geektrust.model.Bill;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BillingServiceTest {
    private final BillingService billingService = new BillingService();

    @Test
    void shouldApplyB4G1CouponWhenQuantityAtLeastFour() {
        Order order = new Order();
        order.addProgram(ProgramType.CERTIFICATION, 1);
        order.addProgram(ProgramType.DEGREE, 1);
        order.addProgram(ProgramType.DIPLOMA, 2);
        order.addCoupon("DEAL_G20");

        Bill bill = billingService.generateBill(order);

        assertEquals(Coupon.B4G1, bill.getCoupon());
        assertEquals(10500f, bill.getTotal(), 0.001);
    }

    @Test
    void shouldApplyDealG20WhenEligible() {
        Order order = new Order();
        order.addProgram(ProgramType.CERTIFICATION, 2); // 6000
        order.addProgram(ProgramType.DEGREE, 1);        // 5000
        order.addCoupon("DEAL_G20");

        Bill bill = billingService.generateBill(order);

        assertEquals(Coupon.DEAL_G20, bill.getCoupon());
        assertEquals(8800f, bill.getTotal(), 0.001); // 11000 - 2200
    }

    @Test
    void shouldApplyDealG5WhenEligible() {
        Order order = new Order();
        order.addProgram(ProgramType.CERTIFICATION, 1); // 3000
        order.addProgram(ProgramType.DIPLOMA, 1);       // 2500
        order.addCoupon("DEAL_G5");

        Bill bill = billingService.generateBill(order);

        assertEquals(Coupon.DEAL_G5, bill.getCoupon());
        assertEquals(5725f, bill.getTotal(), 0.001); // (5500 - 275) + 500 enrollment
    }

    @Test
    void shouldChargeMembershipFeeForProUsers() {
        Order order = new Order();
        order.addProgram(ProgramType.CERTIFICATION, 1);
        order.addProMembership();

        Bill bill = billingService.generateBill(order);

        assertEquals(200f, bill.getMembershipFee(), 0.001);
        assertEquals(3640f, bill.getTotal(), 0.001);
    }

    @Test
    void shouldWaiveEnrollmentFeeWhenTotalAboveThreshold() {
        Order order = new Order();
        order.addProgram(ProgramType.DEGREE, 2); // 10000
        order.addProMembership();
        order.addCoupon("DEAL_G20");

        Bill bill = billingService.generateBill(order);

        assertEquals(0f, bill.getEnrollmentFee(), 0.001);
    }
}
