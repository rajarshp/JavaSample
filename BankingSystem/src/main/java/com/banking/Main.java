package com.banking;

public class Main {
    public static void main(String[] args) {
        AccountService accountService = new AccountService();
        TopTransactionTracker tracker = new TopTransactionTracker(3);
        TransactionFactory factory = new TransactionFactory(accountService, tracker);
        TransactionProcessor processor = new TransactionProcessor(factory);

        String[][] transactions = {
                {"CREATE_ACCOUNT", "Alice"},
                {"CREATE_ACCOUNT", "Bob"},
                {"CREATE_ACCOUNT", "Charlie"},
                {"DEPOSIT", "Alice", "8000"},
                {"DEPOSIT", "Bob", "6000"},
                {"TRANSFER", "Alice", "Bob", "1000"},
                {"TRANSFER", "Bob", "Charlie", "2500"},
                {"TRANSFER", "Alice", "Charlie", "4500"},
//                {"TRANSFER", "Bob", "Charlie", "5000"} // This should fail due to insufficient funds
                {"WITHDRAW", "Bob", "16000"}
        };

        processor.process(transactions);
        accountService.printAccounts();
        tracker.printTopTransactions();
    }
}