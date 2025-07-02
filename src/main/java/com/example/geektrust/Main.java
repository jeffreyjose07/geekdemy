package com.example.geektrust;

import com.example.geektrust.model.*;
import com.example.geektrust.service.BillingService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            return;
        }
        Order order = new Order();
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = reader.readLine()) != null) {
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
                        printBill(new BillingService().generateBill(order));
                        break;
                }
            }
        }
    }

    private static void printBill(Bill bill) {
        System.out.printf("SUB_TOTAL %.2f%n", bill.getSubTotal());
        System.out.printf("COUPON_DISCOUNT %s %.2f%n", bill.getCoupon(), bill.getCouponDiscount());
        System.out.printf("TOTAL_PRO_DISCOUNT %.2f%n", bill.getProDiscount());
        System.out.printf("PRO_MEMBERSHIP_FEE %.2f%n", bill.getMembershipFee());
        System.out.printf("ENROLLMENT_FEE %.2f%n", bill.getEnrollmentFee());
        System.out.printf("TOTAL %.2f%n", bill.getTotal());
    }
}
