import java.util.*;
import java.util.concurrent.*;

public class BankingSystemTest {
    public static void main(String[] args) throws InterruptedException {
        // Initialize BankService
        BankService bank = BankService.getInstance();
        Account a1 = bank.createAccount();
        Account a2 = bank.createAccount();

//        for(int i=0;i<10000;i++){
//            bank.deposit(a1.getAccountId(), 100);
//            bank.deposit(a2.getAccountId(), 200);
//            bank.deposit(a1.getAccountId(), 300);
//            Thread.sleep(10);
//            bank.transfer(a2.getAccountId(), a1.getAccountId(), 50);
//            Thread.sleep(10);
//            bank.transfer(a1.getAccountId(), a2.getAccountId(), 50);
//            Thread.sleep(10);
//            bank.deposit(a1.getAccountId(), 300);
//        }

        ExecutorService executorService = Executors.newFixedThreadPool(4);  // You can adjust the pool size as needed
        try {
            for (int i = 0; i < 10000; i++) {
                // Submit multiple tasks (deposits and transfers)
                Future<?> future1 = executorService.submit(() -> bank.deposit(a1.getAccountId(), 100));
                future1.get();
                Future<?> future2 = executorService.submit(() -> bank.deposit(a2.getAccountId(), 1200));
                future2.get();
                Future<?> future3 = executorService.submit(() -> bank.deposit(a1.getAccountId(), 1300));
                future3.get();
                Future<?> future4 = executorService.submit(() -> bank.deposit(a2.getAccountId(), 100));
                future4.get();
                Future<?> future5 = executorService.submit(() -> bank.deposit(a2.getAccountId(), 500));
                future5.get();
                Future<?> future6 = executorService.submit(() -> bank.transfer(a1.getAccountId(), a2.getAccountId(), 500));
                future6.get();
                Thread.sleep(10);
                // Adding a small delay before the next set of transfers
                Future<?> future7 = executorService.submit(() -> bank.transfer(a1.getAccountId(), a2.getAccountId(), 50));
                future7.get();
                Thread.sleep(10);
                Future<?> future8 = executorService.submit(() -> bank.transfer(a2.getAccountId(), a1.getAccountId(), 5));
                future8.get();
                Thread.sleep(10);
                Future<?> future9 = executorService.submit(() -> bank.deposit(a1.getAccountId(), 30));
                future9.get();
                Thread.sleep(10);
//        executorService.submit(() -> bank.withdraw(a1.getAccountId(), 300));

                Thread.sleep(10);
                int finalI = i;
                Future<?> future10 = executorService.submit(() -> {
                    try {
                        bank.withdraw(a2.getAccountId(), 300);  // This should throw an exception
                    } catch (Exception e) {
                        System.err.println(finalI + " Withdrawal failed for A2: " + e.getMessage());
                    }
                });
                future10.get();
            }

            // Shutdown the executor and wait for tasks to finish
            executorService.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // After all threads complete, get balances and top transactions
        System.out.println("Balance A1: " + a1.getAccountId() + " : " + bank.getBalance(a1.getAccountId()));
        System.out.println("Balance A2: " + a2.getAccountId() + " : " + bank.getBalance(a2.getAccountId()));

        // Get and print the top N transactions by amount
        List<Transaction> top = bank.getTopNTransactions(2, new AmountComparatorStrategy());
        System.out.println("Top 2 transactions by amount:");
        for (Transaction t : top) {
            System.out.println(t.getType() + " | " + t.getAmount() + " | " + t.getDescription());
        }

        // Get and print the top N transactions by time (most recent first)
        System.out.println("Top 2 transactions by time (most recent first):");
        List<Transaction> topByTime = bank.getTopNTransactions(2, new TimeComparatorStrategy());
        for (Transaction t : topByTime) {
            System.out.println(t.getTimestamp() + " | " + t.getType() + " | " + t.getAmount() + " | " + t.getDescription());
        }

    }

}
