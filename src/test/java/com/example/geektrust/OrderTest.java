package com.example.geektrust;

import com.example.geektrust.model.Order;
import com.example.geektrust.model.ProgramType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    @Test
    void shouldReturnCheapestProgramPrice() {
        Order order = new Order();
        order.addProgram(ProgramType.CERTIFICATION, 1);
        order.addProgram(ProgramType.DIPLOMA, 1); // Add DIPLOMA which is cheaper (2500)
        order.addProgram(ProgramType.DEGREE, 1);
        assertEquals(new BigDecimal("2500.00"), order.getCheapestProgramPrice().setScale(2));
    }

    @Test
    void shouldCalculateTotalQuantity() {
        Order order = new Order();
        order.addProgram(ProgramType.CERTIFICATION, 1);
        order.addProgram(ProgramType.DEGREE, 2);
        assertEquals(3, order.getTotalQuantity());
    }

    @Test
    void shouldReturnMembershipFeeWhenProMember() {
        Order order = new Order();
        order.addProMembership();
        assertEquals(new BigDecimal("200.00"), order.membershipFee());
    }

    @Test
    void shouldCalculateProDiscount() {
        Order order = new Order();
        order.addProgram(ProgramType.CERTIFICATION, 1); // 2% of 3000 = 60
        order.addProMembership();
        assertEquals(new BigDecimal("60.00"), order.calculateProDiscount().setScale(2, java.math.RoundingMode.HALF_UP));
    }

    @Test
    void shouldCalculateSubTotal() {
        Order order = new Order();
        order.addProgram(ProgramType.CERTIFICATION, 2); // 2 * 3000 = 6000
        order.addProgram(ProgramType.DIPLOMA, 1);       // 1 * 2500 = 2500
        assertEquals(new BigDecimal("8500.00"), order.calculateSubTotal());
    }
}
