package com.banking;

public interface TransactionCommand {
    void execute(String[] data) throws Exception;
}