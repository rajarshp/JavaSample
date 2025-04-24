import java.util.Comparator;

public class AmountComparatorStrategy implements TransactionComparatorStrategy {
    public Comparator<Transaction> getComparator() {
        return Comparator.comparingDouble(Transaction::getAmount);
    }
}