package com.banking;

public class TransactionProcessor {
    private final TransactionFactory factory;

    public TransactionProcessor(TransactionFactory factory) {
        this.factory = factory;
    }

    public void process(String[][] txns) {
        for (String[] txn : txns) {
            try {
                TransactionCommand command = factory.getCommand(txn[0]);
                command.execute(txn);
            } catch (Exception e) {
                System.err.println("Transaction failed: " + e.getMessage());
                System.err.println("Transaction details: " + String.join(", ", txn));
            }
        }
    }
}