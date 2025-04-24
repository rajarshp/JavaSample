package com.banking;

import java.util.PriorityQueue;

public class TopTransactionTracker {
    private final int size;
    private final PriorityQueue<Transaction> minHeap;

    public TopTransactionTracker(int size) {
        this.size = size;
        this.minHeap = new PriorityQueue<>();
    }

    public void track(Transaction t) {
        minHeap.offer(t);
        if (minHeap.size() > size) {
            minHeap.poll();
        }
    }

    public void printTopTransactions() {
        System.out.println("Top " + size + " Transactions:");
        minHeap.stream()
               .sorted((a, b) -> b.getAmount().compareTo(a.getAmount()))
               .forEach(System.out::println);
    }
}