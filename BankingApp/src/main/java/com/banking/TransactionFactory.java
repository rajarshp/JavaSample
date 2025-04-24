package com.banking;

import java.util.HashMap;
import java.util.Map;

public class TransactionFactory {
    private final Map<String, TransactionCommand> commandMap = new HashMap<>();

    public TransactionFactory(AccountService service, TopTransactionTracker tracker) {
        commandMap.put("DEPOSIT", new DepositCommand(service, tracker));
        commandMap.put("WITHDRAW", new WithdrawCommand(service, tracker));
        commandMap.put("TRANSFER", new TransferCommand(service, tracker));
        commandMap.put("CREATE_ACCOUNT", data -> service.createAccount(data[1]));
    }

    public TransactionCommand getCommand(String type) {
        return commandMap.getOrDefault(type, data -> {
            throw new UnsupportedOperationException("Invalid transaction type: " + type);
        });
    }
}