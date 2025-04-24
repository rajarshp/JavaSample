// Design Pattern: Strategy Pattern for different sorting strategies
import java.util.Comparator;

public interface TransactionComparatorStrategy {
    Comparator<Transaction> getComparator();
}