package com.example.geektrust.command;

import com.example.geektrust.model.Order;
import com.example.geektrust.model.ProgramType;
import com.example.geektrust.service.BillingService;

public class AddProgrammeCommand implements Command {
    private final ProgramType program;
    private final int quantity;

    public AddProgrammeCommand(ProgramType program, int quantity) {
        this.program = program;
        this.quantity = quantity;
    }

    @Override
    public void execute(Order order, BillingService billingService) {
        order.addProgram(program, quantity);
    }
}
