package com.banking;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

public class AccountService {
    private final ConcurrentHashMap<String, Account> accounts = new ConcurrentHashMap<>();

    public void createAccount(String name) {
        accounts.putIfAbsent(name, new Account(name));
    }

    public boolean deposit(String name, BigDecimal amt) {
        Account acc = accounts.get(name);
        if (acc == null) return false;
        acc.deposit(amt);
        return true;
    }

    public boolean withdraw(String name, BigDecimal amt) {
        Account acc = accounts.get(name);
        if (acc == null || acc.getBalance().compareTo(amt) < 0) return false;
        acc.withdraw(amt);
        return true;
    }

    public boolean transfer(String from, String to, BigDecimal amt) {
        if (from.equals(to)) return false;
        Account accFrom = accounts.get(from);
        Account accTo = accounts.get(to);
        if (accFrom == null || accTo == null) return false;

        Account first = from.compareTo(to) < 0 ? accFrom : accTo;
        Account second = from.compareTo(to) < 0 ? accTo : accFrom;

        synchronized (first) {
            synchronized (second) {
                if (accFrom.withdraw(amt)) {
                    accTo.deposit(amt);
                    return true;
                }
                return false;
            }
        }
    }

    public Account getAccount(String name) {
        return accounts.get(name);
    }

    public void printAccounts() {
        accounts.forEach((k, v) ->
                System.out.println("Account: " + k + ", Balance: " + v.getBalance()));
    }
}