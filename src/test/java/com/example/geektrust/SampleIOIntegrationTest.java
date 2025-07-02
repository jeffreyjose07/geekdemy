package com.example.geektrust;

import com.example.geektrust.command.CommandProcessor;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Integration tests that execute the application using the sample input files
 * provided in the repository. The tests verify that the program output matches
 * the expected results for each sample scenario.
 */
public class SampleIOIntegrationTest {

    private String runApp(Path inputFile) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));
        new CommandProcessor().run(inputFile.toString());
        System.setOut(original);
        return out.toString().replace("\r", "").trim();
    }

    @Test
    void sampleInput1() throws Exception {
        Path input = Paths.get("sample_input", "input1.txt");
        String expected = String.join("\n",
                "SUB_TOTAL 13000.00",
                "COUPON_DISCOUNT B4G1 2500.00",
                "TOTAL_PRO_DISCOUNT 0.00",
                "PRO_MEMBERSHIP_FEE 0.00",
                "ENROLLMENT_FEE 0.00",
                "TOTAL 10500.00");
        assertEquals(expected, runApp(input));
    }

    @Test
    void sampleInput2() throws Exception {
        Path input = Paths.get("sample_input", "input2.txt");
        String expected = String.join("\n",
                "SUB_TOTAL 17790.00",
                "COUPON_DISCOUNT B4G1 2475.00",
                "TOTAL_PRO_DISCOUNT 410.00",
                "PRO_MEMBERSHIP_FEE 200.00",
                "ENROLLMENT_FEE 0.00",
                "TOTAL 15315.00");
        assertEquals(expected, runApp(input));
    }

    @Test
    void sampleInput3() throws Exception {
        Path input = Paths.get("sample_input", "input3.txt");
        String expected = String.join("\n",
                "SUB_TOTAL 5615.00",
                "COUPON_DISCOUNT NONE 0.00",
                "TOTAL_PRO_DISCOUNT 85.00",
                "PRO_MEMBERSHIP_FEE 200.00",
                "ENROLLMENT_FEE 500.00",
                "TOTAL 6115.00");
        assertEquals(expected, runApp(input));
    }

    @Test
    void sampleInput4() throws Exception {
        Path input = Paths.get("sample_input", "input4.txt");
        String expected = String.join("\n",
                "SUB_TOTAL 8555.00",
                "COUPON_DISCOUNT DEAL_G5 427.75",
                "TOTAL_PRO_DISCOUNT 145.00",
                "PRO_MEMBERSHIP_FEE 200.00",
                "ENROLLMENT_FEE 0.00",
                "TOTAL 8127.25");
        assertEquals(expected, runApp(input));
    }

    @Test
    void sampleInput5() throws Exception {
        Path input = Paths.get("sample_input", "input5.txt");
        String expected = String.join("\n",
                "SUB_TOTAL 10000.00",
                "COUPON_DISCOUNT DEAL_G20 2000.00",
                "TOTAL_PRO_DISCOUNT 0.00",
                "PRO_MEMBERSHIP_FEE 0.00",
                "ENROLLMENT_FEE 0.00",
                "TOTAL 8000.00");
        assertEquals(expected, runApp(input));
    }
}

