import java.util.Comparator;

public class TimeComparatorStrategy implements TransactionComparatorStrategy {
    public Comparator<Transaction> getComparator() {
        return Comparator.comparing(Transaction::getTimestamp);
    }
}