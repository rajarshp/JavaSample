import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Account {
    private final String accountId;
    private double balance;
    private final List<Transaction> transactions = new ArrayList<>();

    public Account(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction(TransactionType.DEPOSIT, amount, "Deposit to "+getAccountId()));
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return List.copyOf(transactions);
    }

    private void debit(double amount) {
        if (balance < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balance -= amount;
    }

    private void credit(double amount) {
        balance += amount;
    }

    private void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    // The transfer method is now synchronized
    public void transfer(Account toAccount, double amount) {
        this.debit(amount);
        toAccount.credit(amount);
        Transaction transaction = new Transaction(TransactionType.TRANSFER, amount, "Transfer to " + toAccount.getAccountId());
        this.addTransaction(transaction);
        toAccount.addTransaction(new Transaction(TransactionType.TRANSFER, amount, "Received from " + this.getAccountId()));
    }

    public void withdraw(double amount) {
        if (balance < amount) {
            throw new IllegalArgumentException("Insufficient funds for withdrawal");
        }
        balance -= amount;
        transactions.add(new Transaction(TransactionType.WITHDRAW, amount, "Withdrawal"));
    }
}
