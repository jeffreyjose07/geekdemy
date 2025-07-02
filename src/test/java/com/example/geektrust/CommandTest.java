package com.example.geektrust;

import com.example.geektrust.command.*;
import com.example.geektrust.model.Coupon;
import com.example.geektrust.model.Order;
import com.example.geektrust.model.ProgramType;
import com.example.geektrust.service.BillingService;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {
    private final BillingService billingService = new BillingService();

    @Test
    void shouldAddProgramThroughCommand() {
        Order order = new Order();
        Command cmd = new AddProgrammeCommand(ProgramType.DIPLOMA, 2);
        cmd.execute(order, billingService);
        assertEquals(2, order.getItems().get(0).getQuantity());
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
    void shouldPrintBillThroughCommand() {
        Order order = new Order();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));
        Command cmd = new PrintBillCommand();
        cmd.execute(order, billingService);
        System.setOut(original);
        assertTrue(out.toString().contains("TOTAL"));
    }
}
