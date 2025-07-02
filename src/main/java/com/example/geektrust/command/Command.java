package com.example.geektrust.command;

import com.example.geektrust.model.Order;
import com.example.geektrust.service.BillingService;

public interface Command {
    void execute(Order order, BillingService billingService);
}
