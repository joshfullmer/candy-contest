import java.io.PrintWriter;

/**
 * An API for the simple Candy Contest.
 *
 * <p>Implementations do not need to be thread-safe.</p>
 */
public interface CandyContest {

    /**
     * Process a sale for the salesman represented
     * by the given string.
     *
     * @param salesmanName Identifier for salesman
     * @param barsSold number of candy bars sold
     */
    void processSale(String salesmanName, int barsSold);

    /**
     * Return the string key representing the salesman
     * who would be considered the top salesman, were no
     * further invocations of {@link #processSale(String, int)}
     * to occur.
     *
     * @return a salesman identifier for the contest's
     * top salesman, or null if no individual salesman can be
     * considered the clear winner.
     */
    String getTopSalesman();

    /**
     * Output the current salesman ranking in the form
     * of CSV sorted by the count of candy bars sold in descending
     * order, if there is a tie, the salesmen should be sorted
     * alphabetically. The first row should declare the column headings.
     *
     * The following example specifies the expected
     * content with sample salesmen. Y signifies the top salesman,
     * N is for all others
     *
     * Salesman,Candy Bars Sold,Top Salesman
     * Joey,15,Y
     * Bob,5,N
     *
     * NOTE: A contest with no sales should produce only the header
     *
     * @param writer used to output sales ranking.
     */
    void printSalesRanking(PrintWriter writer);

}
