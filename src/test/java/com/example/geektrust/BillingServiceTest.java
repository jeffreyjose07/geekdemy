package com.example.geektrust;

import com.example.geektrust.model.Coupon;
import com.example.geektrust.model.Order;
import com.example.geektrust.model.ProgramType;
import java.math.BigDecimal;
import com.example.geektrust.service.BillingService;
import com.example.geektrust.model.Bill;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BillingServiceTest {
    private final BillingService billingService = new BillingService();

    @Test
    void shouldApplyB4G1CouponWhenQuantityAtLeastFour() {
        Order order = new Order();
        order.addProgram(ProgramType.CERTIFICATION, 1); // 3000
        order.addProgram(ProgramType.DEGREE, 1);        // 5000
        order.addProgram(ProgramType.DIPLOMA, 2);       // 5000
        // Total: 13000
        // B4G1 discount: 2500 (cheapest program)
        // Expected total: 10500
        order.addCoupon("B4G1");

        Bill bill = billingService.generateBill(order);

        assertEquals(Coupon.B4G1, bill.getCoupon());
        assertEquals(new BigDecimal("10500.00"), bill.getTotal().setScale(2, java.math.RoundingMode.HALF_UP));
    }

    @Test
    void shouldApplyDealG20WhenEligible() {
        Order order = new Order();
        order.addProgram(ProgramType.CERTIFICATION, 2); // 6000
        order.addProgram(ProgramType.DEGREE, 1);        // 5000
        // Subtotal: 11000
        // DEAL_G20 discount: 20% of 11000 = 2200
        // Total: 11000 - 2200 = 8800
        order.addCoupon("DEAL_G20");

        Bill bill = billingService.generateBill(order);

        assertEquals(Coupon.DEAL_G20, bill.getCoupon());
        assertEquals(new BigDecimal("8800.00"), bill.getTotal().setScale(2, java.math.RoundingMode.HALF_UP));
    }

    @Test
    void shouldApplyDealG5WhenEligible() {
        Order order = new Order();
        order.addProgram(ProgramType.CERTIFICATION, 1); // 3000
        order.addProgram(ProgramType.DIPLOMA, 1);       // 2500
        // Subtotal: 5500
        // DEAL_G5 discount: 5% of 5500 = 275
        // Enrollment fee: 500 (since 5500 - 275 = 5225 < 6666)
        // Total: 5500 - 275 + 500 = 5725
        order.addCoupon("DEAL_G5");

        Bill bill = billingService.generateBill(order);

        assertEquals(Coupon.DEAL_G5, bill.getCoupon());
        assertEquals(new BigDecimal("5725.00"), bill.getTotal().setScale(2, java.math.RoundingMode.HALF_UP));
    }

    @Test
    void shouldChargeMembershipFeeForProUsers() {
        Order order = new Order();
        order.addProgram(ProgramType.CERTIFICATION, 1);
        order.addProMembership();

        Bill bill = billingService.generateBill(order);

        assertEquals(new BigDecimal("200.00"), bill.getMembershipFee());
        assertEquals(new BigDecimal("3640.00"), bill.getTotal());
    }

    @Test
    void shouldWaiveEnrollmentFeeWhenTotalAboveThreshold() {
        Order order = new Order();
        order.addProgram(ProgramType.DEGREE, 2); // 10000
        order.addProMembership();
        // Subtotal: 10000
        // Pro discount: 3% of 10000 = 300
        // DEAL_G20 discount: 20% of 10000 = 2000
        // Total before enrollment: 10000 - 300 - 2000 + 200 (membership) = 7900
        // Since 7900 > 6666, enrollment fee is waived
        order.addCoupon("DEAL_G20");

        Bill bill = billingService.generateBill(order);

        assertEquals(BigDecimal.ZERO.setScale(2), bill.getEnrollmentFee().setScale(2));
    }
}
