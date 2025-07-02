package com.example.geektrust;

import com.example.geektrust.model.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CouponTest {
    @Test
    void b4g1ShouldReturnCheapestProgramPrice() {
        Order order = new Order();
        order.addProgram(ProgramType.CERTIFICATION, 1); // 3000
        order.addProgram(ProgramType.DEGREE, 1);        // 5000
        order.addProgram(ProgramType.DIPLOMA, 2);       // 2500 x2

        BigDecimal discount = Coupon.B4G1.discountAmount(order, order.calculateSubTotal());
        assertEquals(new BigDecimal("2500.00"), discount.setScale(2));
    }

    @Test
    void dealG20ShouldApplyTwentyPercent() {
        Order order = new Order();
        order.addProgram(ProgramType.DEGREE, 2); // 10000
        order.addCoupon("DEAL_G20");
        BigDecimal subTotal = order.calculateSubTotal();
        BigDecimal discount = Coupon.DEAL_G20.discountAmount(order, subTotal);
        assertEquals(new BigDecimal("2000.00"), discount.setScale(2, java.math.RoundingMode.HALF_UP));
    }

    @Test
    void dealG5ShouldApplyFivePercent() {
        Order order = new Order();
        order.addProgram(ProgramType.CERTIFICATION, 1); // 3000
        order.addProgram(ProgramType.DIPLOMA, 1);       // 2500
        order.addCoupon("DEAL_G5");
        BigDecimal subTotal = order.calculateSubTotal();
        BigDecimal discount = Coupon.DEAL_G5.discountAmount(order, subTotal);
        assertEquals(new BigDecimal("275.00"), discount.setScale(2, java.math.RoundingMode.HALF_UP));
    }
}
