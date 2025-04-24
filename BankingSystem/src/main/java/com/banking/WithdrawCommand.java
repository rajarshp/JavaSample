package com.banking;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;

public class WithdrawCommand implements TransactionCommand {
    private final AccountService accountService;
    private final TopTransactionTracker tracker;

    public WithdrawCommand(AccountService service, TopTransactionTracker tracker) {
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

        if (!accountService.withdraw(accName, amount)) {
            throw new Exception("Unable to complete transaction");
        }

        tracker.track(new Transaction("WITHDRAW", accName, accName, amount));
    }
}
