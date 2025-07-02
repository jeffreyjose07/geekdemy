package com.example.geektrust.command;

import com.example.geektrust.model.Order;
import com.example.geektrust.service.BillingService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CommandProcessor {
    private final BillingService billingService = new BillingService();
    private final CommandFactory factory = new CommandFactory();

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
        Command command = factory.get(tokens);
        command.execute(order, billingService);
    }
}
