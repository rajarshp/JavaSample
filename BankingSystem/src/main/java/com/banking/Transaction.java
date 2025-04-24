package com.banking;

import java.math.BigDecimal;

public class Transaction implements Comparable<Transaction> {
    private final String type;
    private final String from;
    private final String to;
    private final BigDecimal amount;

    public Transaction(String type, String from, String to, BigDecimal amount) {
        this.type = type;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public int compareTo(Transaction o) {
        return this.amount.compareTo(o.amount);
    }

    @Override
    public String toString() {
        return type + ": from=" + from + ", to=" + to + ", amount=" + amount;
    }
}