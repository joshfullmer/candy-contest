import java.util.Comparator;

public class ReverseSortSalesmenComparator implements Comparator<Salesman> {
    @Override
    public int compare(Salesman s1, Salesman s2) {
        int barsDiff = s2.getBarsSold() - s1.getBarsSold();
        if (barsDiff == 0) {
            return s1.getSalesmanName().compareTo(s2.getSalesmanName());
        }
        return barsDiff;
    }
}