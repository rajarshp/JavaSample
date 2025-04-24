package com.banking;

import java.math.BigDecimal;

public class DepositCommand implements TransactionCommand {
    private final AccountService accountService;
    private final TopTransactionTracker tracker;

    public DepositCommand(AccountService service, TopTransactionTracker tracker) {
        this.accountService = service;
        this.tracker = tracker;
    }

    @Override
    public void execute(String[] data) throws Exception {
        String accName = data[1];
        BigDecimal amount = new BigDecimal(data[2]);
        Account account = accountService.getAccount(accName);

        if (account == null) {
            throw new Exception("Invalid account");
        }

        accountService.deposit(accName, amount);
        tracker.track(new Transaction("DEPOSIT", accName, accName, amount));
    }
}