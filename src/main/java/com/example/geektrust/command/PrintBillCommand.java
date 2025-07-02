package com.example.geektrust.command;

import com.example.geektrust.model.Bill;
import com.example.geektrust.model.Order;
import com.example.geektrust.service.BillPrinter;
import com.example.geektrust.service.BillingService;

public class PrintBillCommand implements Command {
    private final BillPrinter printer = new BillPrinter();

    @Override
    public void execute(Order order, BillingService billingService) {
        Bill bill = billingService.generateBill(order);
        printer.print(bill);
    }
}
