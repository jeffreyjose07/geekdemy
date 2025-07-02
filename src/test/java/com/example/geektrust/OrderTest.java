package com.example.geektrust;

import com.example.geektrust.model.Order;
import com.example.geektrust.model.ProgramType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderTest {
    @Test
    void shouldReturnCheapestProgramPrice() {
        Order order = new Order();
        order.addProgram(ProgramType.CERTIFICATION, 1);
        order.addProgram(ProgramType.DEGREE, 1);
        assertEquals(3000f, order.getCheapestProgramPrice(), 0.001);
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
        assertEquals(200f, order.membershipFee(), 0.001);
    }

    @Test
    void shouldCalculateProDiscount() {
        Order order = new Order();
        order.addProgram(ProgramType.CERTIFICATION, 1); // 2% of 3000 = 60
        order.addProMembership();
        assertEquals(60f, order.calculateProDiscount(), 0.001);
    }
}
