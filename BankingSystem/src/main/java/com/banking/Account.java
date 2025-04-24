package com.banking;

import java.math.BigDecimal;

public class Account {
    private final String name;
    private BigDecimal balance;

    public Account(String name) {
        this.name = name;
        this.balance = BigDecimal.ZERO;
    }

    public synchronized void deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public synchronized boolean withdraw(BigDecimal amount) {
        if (this.balance.compareTo(amount) >= 0) {
            this.balance = this.balance.subtract(amount);
            return true;
        }
        return false;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}