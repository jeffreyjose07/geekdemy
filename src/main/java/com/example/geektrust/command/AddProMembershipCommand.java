package com.example.geektrust.command;

import com.example.geektrust.model.Order;
import com.example.geektrust.service.BillingService;

public class AddProMembershipCommand implements Command {
    @Override
    public void execute(Order order, BillingService billingService) {
        order.addProMembership();
    }
}
