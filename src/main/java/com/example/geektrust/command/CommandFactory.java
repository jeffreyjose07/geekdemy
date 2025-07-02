package com.example.geektrust.command;

import com.example.geektrust.model.ProgramType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class CommandFactory {
    private final Map<String, Function<String[], Command>> creators = new HashMap<>();

    public CommandFactory() {
        creators.put("ADD_PROGRAMME", tokens -> new AddProgrammeCommand(
                ProgramType.valueOf(tokens[1]), Integer.parseInt(tokens[2])));
        creators.put("ADD_PRO_MEMBERSHIP", tokens -> new AddProMembershipCommand());
        creators.put("APPLY_COUPON", tokens -> new ApplyCouponCommand(tokens[1]));
        creators.put("PRINT_BILL", tokens -> new PrintBillCommand());
    }

    public Command get(String[] tokens) {
        Function<String[], Command> creator = creators.get(tokens[0]);
        if (creator == null) {
            return new NoOpCommand();
        }
        return creator.apply(tokens);
    }
}
