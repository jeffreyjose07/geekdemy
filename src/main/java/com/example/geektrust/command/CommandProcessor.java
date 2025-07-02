package com.example.geektrust.command;

import com.example.geektrust.model.Bill;
import com.example.geektrust.model.Order;
import com.example.geektrust.model.ProgramType;
import com.example.geektrust.service.BillingService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CommandProcessor {
    private final BillingService billingService = new BillingService();

    public void run(String filePath) {
        Order order = new Order();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processLine(order, line.trim());
            }
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        }
    }

    private void processLine(Order order, String line) {
        if (line.isEmpty()) {
            return;
        }
        String[] tokens = line.split(" ");
        switch (tokens[0]) {
            case "ADD_PROGRAMME":
                order.addProgram(ProgramType.valueOf(tokens[1]), Integer.parseInt(tokens[2]));
                break;
            case "ADD_PRO_MEMBERSHIP":
                order.addProMembership();
                break;
            case "APPLY_COUPON":
                order.addCoupon(tokens[1]);
                break;
            case "PRINT_BILL":
                printBill(billingService.generateBill(order));
                break;
            default:
                // unknown command ignored
        }
    }

    private void printBill(Bill bill) {
        System.out.printf("SUB_TOTAL %.2f%n", bill.getSubTotal());
        System.out.printf("COUPON_DISCOUNT %s %.2f%n", bill.getCoupon(), bill.getCouponDiscount());
        System.out.printf("TOTAL_PRO_DISCOUNT %.2f%n", bill.getProDiscount());
        System.out.printf("PRO_MEMBERSHIP_FEE %.2f%n", bill.getMembershipFee());
        System.out.printf("ENROLLMENT_FEE %.2f%n", bill.getEnrollmentFee());
        System.out.printf("TOTAL %.2f%n", bill.getTotal());
    }
}
