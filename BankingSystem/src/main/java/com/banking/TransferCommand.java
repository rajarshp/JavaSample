package com.banking;

import java.math.BigDecimal;

public class TransferCommand implements TransactionCommand {
    private final AccountService accountService;
    private final TopTransactionTracker tracker;

    public TransferCommand(AccountService service, TopTransactionTracker tracker) {
        this.accountService = service;
        this.tracker = tracker;
    }

    @Override
    public void execute(String[] data) throws Exception {
        String from = data[1], to = data[2];
        BigDecimal amt = new BigDecimal(data[3]);

        Account fromAccount = accountService.getAccount(from);
        Account toAccount = accountService.getAccount(to);

        if (fromAccount == null || toAccount == null) {
            throw new Exception("Invalid account");
        }

        if (!accountService.transfer(from, to, amt)) {
            throw new Exception("Unable to complete transaction");
        }

        tracker.track(new Transaction("TRANSFER", from, to, amt));
    }
}