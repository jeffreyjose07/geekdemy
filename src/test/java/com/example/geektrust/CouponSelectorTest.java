package com.example.geektrust;

import com.example.geektrust.model.Order;
import com.example.geektrust.model.ProgramType;
import com.example.geektrust.service.CouponSelector;
import com.example.geektrust.model.Coupon;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CouponSelectorTest {
    private final CouponSelector selector = new CouponSelector();

    @Test
    void returnsB4G1WhenQuantityAtLeastFour() {
        Order order = new Order();
        order.addProgram(ProgramType.CERTIFICATION, 1);
        order.addProgram(ProgramType.DEGREE, 1);
        order.addProgram(ProgramType.DIPLOMA, 2);
        assertEquals(Coupon.B4G1, selector.selectCoupon(order, order.calculateSubTotal()));
    }

    @Test
    void selectsBestCouponBasedOnDiscount() {
        Order order = new Order();
        order.addProgram(ProgramType.DEGREE, 2); // subtotal 10000
        order.addCoupon("DEAL_G5");
        order.addCoupon("DEAL_G20");

        Coupon coupon = selector.selectCoupon(order, order.calculateSubTotal());
        assertEquals(Coupon.DEAL_G20, coupon);
    }
}
