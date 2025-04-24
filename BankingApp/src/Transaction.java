import java.time.LocalDateTime;

// SOLID: Single Responsibility - Represents a single transaction (Immutable)
public class Transaction {
    private final TransactionType type;
    private final double amount;
    private final LocalDateTime timestamp;
    private final String description;

    public Transaction(TransactionType type, double amount, String description) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }

    public TransactionType getType() { return type; }
    public double getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getDescription() { return description; }
}