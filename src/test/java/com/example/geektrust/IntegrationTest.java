package com.example.geektrust;

import com.example.geektrust.command.CommandProcessor;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {
    @Test
    void testCase1() throws Exception {
        Path input = Files.createTempFile("input", ".txt");
        Files.write(input, Arrays.asList(
                "ADD_PROGRAMME CERTIFICATION 1",
                "ADD_PROGRAMME DEGREE 1",
                "ADD_PROGRAMME DIPLOMA 2",
                "APPLY_COUPON DEAL_G20",
                "PRINT_BILL"
        ));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));
        new CommandProcessor().run(input.toString());
        System.setOut(original);

        String expected = String.join("\n",
                "SUB_TOTAL 13000.00",
                "COUPON_DISCOUNT B4G1 2500.00",
                "TOTAL_PRO_DISCOUNT 0.00",
                "PRO_MEMBERSHIP_FEE 0.00",
                "ENROLLMENT_FEE 0.00",
                "TOTAL 10500.00",
                "");
        String actual = out.toString().replace("\r", "");
        assertEquals(expected, actual);
    }

    @Test
    void testCase2() throws Exception {
        Path input = Files.createTempFile("input", ".txt");
        Files.write(input, Arrays.asList(
                "ADD_PROGRAMME DEGREE 1",
                "ADD_PROGRAMME DIPLOMA 2",
                "APPLY_COUPON DEAL_G20",
                "APPLY_COUPON DEAL_G5",
                "PRINT_BILL"
        ));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));
        new CommandProcessor().run(input.toString());
        System.setOut(original);

        String expected = String.join("\n",
                "SUB_TOTAL 10000.00",
                "COUPON_DISCOUNT DEAL_G20 2000.00",
                "TOTAL_PRO_DISCOUNT 0.00",
                "PRO_MEMBERSHIP_FEE 0.00",
                "ENROLLMENT_FEE 0.00",
                "TOTAL 8000.00",
                "");
        String actual = out.toString().replace("\r", "");
        assertEquals(expected, actual);
    }
    
    @Test
    void testCase3_WithProMembership() throws Exception {
        Path input = Files.createTempFile("input", ".txt");
        Files.write(input, Arrays.asList(
                "ADD_PROGRAMME CERTIFICATION 1",
                "ADD_PROGRAMME DEGREE 1",
                "ADD_PRO_MEMBERSHIP",
                "PRINT_BILL"
        ));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));
        new CommandProcessor().run(input.toString());
        System.setOut(original);

        String expected = String.join("\n",
                "SUB_TOTAL 7990.00",
                "COUPON_DISCOUNT NONE 0.00",
                "TOTAL_PRO_DISCOUNT 210.00",
                "PRO_MEMBERSHIP_FEE 200.00",
                "ENROLLMENT_FEE 0.00",
                "TOTAL 7990.00",
                "");
        String actual = out.toString().replace("\r", "");
        assertEquals(expected, actual);
    }
}
