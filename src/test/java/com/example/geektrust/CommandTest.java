package com.example.geektrust;

import com.example.geektrust.command.*;
import com.example.geektrust.model.*;
import com.example.geektrust.service.BillingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {
    private BillingService billingService;
    
    @BeforeEach
    void setUp() {
        billingService = new BillingService();
    }
    
    @Test
    void shouldAddProgramThroughCommand() {
        Order order = new Order();
        Command cmd = new AddProgrammeCommand(ProgramType.DIPLOMA, 2);
        cmd.execute(order, billingService);
        assertEquals(2, order.getItems().get(0).getQuantity());
        assertEquals(ProgramType.DIPLOMA, order.getItems().get(0).getProgram());
    }

    @Test
    void shouldAddProMembershipThroughCommand() {
        Order order = new Order();
        Command cmd = new AddProMembershipCommand();
        cmd.execute(order, billingService);
        assertTrue(order.hasProMembership());
    }

    @Test
    void shouldAddCouponThroughCommand() {
        Order order = new Order();
        Command cmd = new ApplyCouponCommand("DEAL_G20");
        cmd.execute(order, billingService);
        assertTrue(order.hasCoupon(Coupon.DEAL_G20));
    }
    
    @Test
    void shouldNotAddInvalidCoupon() {
        Order order = new Order();
        Command cmd = new ApplyCouponCommand("INVALID_COUPON");
        cmd.execute(order, billingService);
        assertTrue(order.getCoupons().isEmpty());
    }

    @Test
    void shouldPrintBillThroughCommand() {
        Order order = new Order();
        order.addProgram(ProgramType.CERTIFICATION, 1);
        order.addProgram(ProgramType.DEGREE, 2);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));
        
        Command cmd = new PrintBillCommand();
        cmd.execute(order, billingService);
        
        System.setOut(original);
        
        String expected = String.join("\n",
                "SUB_TOTAL 13000.00",
                "COUPON_DISCOUNT NONE 0.00",
                "TOTAL_PRO_DISCOUNT 0.00",
                "PRO_MEMBERSHIP_FEE 0.00",
                "ENROLLMENT_FEE 0.00",
                "TOTAL 13000.00",
                "");
        assertEquals(expected, out.toString().replace("\r", ""));
    }
}
