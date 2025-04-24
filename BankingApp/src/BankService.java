// Design Pattern: Facade Pattern - exposes simplified API
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class BankService {
    private final Map<String, Account> accounts = new HashMap<>();
    private static final Object lock = new Object();

    private BankService() {}

    private static final class InstanceHolder {
        private static final BankService instance = new BankService();
    }

    public static BankService getInstance(){

        return InstanceHolder.instance;
    }

    public Account createAccount() {
        String id = String.valueOf(ThreadLocalRandom.current().nextLong(1000000000L, 10000000000L));
        if (accounts.containsKey(id)) throw new IllegalArgumentException("Duplicate account");
        Account account = new Account(id);
        accounts.put(id, account);
        return account;
    }

    public synchronized void deposit(String accountId, double amount) {

        getAccount(accountId).deposit(amount);
        System.out.println(amount + " Deposited");
        System.out.println("Balance: " + accountId + " : " + getBalance(accountId));
    }

    public synchronized void transfer(String fromId, String toId, double amount) {
        Account from = getAccount(fromId);
        Account to = getAccount(toId);
        from.transfer(to, amount);

        System.out.println("Transfer Completed: " +amount);
        System.out.println("Source Balance: " + from.getAccountId() + " : " + getBalance(from.getAccountId()));
        System.out.println("Target Balance: " + to.getAccountId() + " : " + getBalance(to.getAccountId()));
    }

    public synchronized List<Transaction> getTopNTransactions(int n, TransactionComparatorStrategy strategy) {
        PriorityQueue<Transaction> topTransactions = new PriorityQueue<>(n, strategy.getComparator());

        // Iterate over each account and gather transactions
        for (Account account : accounts.values()) {
            List<Transaction> transactions = account.getTransactions();
            for (Transaction transaction : transactions) {
                if (topTransactions.size() < n) {
                    topTransactions.offer(transaction);
                } else if (strategy.getComparator().compare(transaction, topTransactions.peek()) > 0) {
                    topTransactions.poll();
                    topTransactions.offer(transaction);
                }
            }
        }

        // Convert the heap to a list and return the sorted result
        List<Transaction> result = new ArrayList<>(topTransactions);
        result.sort(strategy.getComparator().reversed());
        return result;
    }

    public synchronized double getBalance(String acId){
        return getAccount(acId).getBalance();
    }

    private Account getAccount(String id) {
        Account acc = accounts.get(id);
        if (acc == null) throw new IllegalArgumentException("Account not found");
        return acc;
    }

    public synchronized void withdraw(String accountId, double amount) {
        getAccount(accountId).withdraw(amount);
        System.out.println(amount + " withdrawn");
        System.out.println("Balance: " + accountId + " : " + getBalance(accountId));
    }
}