package com.example.geektrust;

import com.example.geektrust.model.Bill;
import com.example.geektrust.model.Coupon;
import com.example.geektrust.service.BillPrinter;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BillPrinterTest {
    @Test
    void shouldPrintFormattedBill() {
        Bill bill = new Bill(
                new BigDecimal("100.00"),
                Coupon.DEAL_G5,
                new BigDecimal("5.00"),
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                new BigDecimal("10.00"),
                new BigDecimal("105.00")
        );

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));

        new BillPrinter().print(bill);
        System.setOut(original);

        String expected = String.join("\n",
                "SUB_TOTAL 100.00",
                "COUPON_DISCOUNT DEAL_G5 5.00",
                "TOTAL_PRO_DISCOUNT 0.00",
                "PRO_MEMBERSHIP_FEE 0.00",
                "ENROLLMENT_FEE 10.00",
                "TOTAL 105.00",
                "");
        assertEquals(expected, out.toString().replace("\r", ""));
    }
}
