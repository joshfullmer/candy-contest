import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.fail;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

public class SimpleChocolateContestTest {

    private ChocolateContest chocolateContest;
    private static final String EOL = System.getProperty("line.separator");

    @BeforeMethod
    protected void setUp() {
        chocolateContest = new ChocolateContest();
    }

    @Test
    public void testSaleForNullSalesmanThrowsUncheckedException() {
        try {
            chocolateContest.processSale(null, 0);
            fail("should have thrown an exception");
        } catch(NullPointerException npe){
            fail("A non NullPointerException is preferred");
        }  catch ( RuntimeException e ) {
            //success
        }
    }

    @Test
    public void testSaleForWhitespaceSalesmanStringThrowsUncheckedException() {
        try {
            chocolateContest.processSale(" ", 0);
            fail("should have thrown an exception");
        } catch(NullPointerException npe){
            fail("A non NullPointerException is preferred");
        }  catch ( RuntimeException e ) {
            //success
        }
    }

    @Test
    public void testSaleForEmptySalesmanStringThrowsUncheckedException() {
        try {
            chocolateContest.processSale("", 0);
            fail("should have thrown an exception");
        } catch(NullPointerException npe){
            fail("A non NullPointerException is preferred");
        }  catch ( RuntimeException e ) {
            //success
        }
    }

    @Test
    public void testNoTopsalesmanIfNoSales() {
        assertNull(chocolateContest.getTopSalesman(), "Should not have a top salesman if no candy bars have been sold");
    }

    @Test
    public void testNoTopsalesmanIfTie() {
        chocolateContest.processSale("JOE_SMITH", 1);
        chocolateContest.processSale("JANE_SMITH", 1);
        assertNull(chocolateContest.getTopSalesman(), "Should not have a top salesman if highest salesmen are in a tie");
    }

    @Test
    public void testTopsalesmanIsSalesmanWithMostSales() {
        chocolateContest.processSale("JOE_SMITH", 1);
        chocolateContest.processSale("JANE_SMITH", 1);
        chocolateContest.processSale("JOE_SMITH", 1);
        assertEquals(chocolateContest.getTopSalesman(), "JOE_SMITH", "Calculated incorrect top salesman");
    }

    @Test
    public void testOutputsEmptyTableIfNoSales() {
        System.out.println(chocolateContest.hashCode());
        String expectedCSV = "Salesman,Candy Bars Sold,Top Salesman";
        assertEquals(reportAsString(chocolateContest).trim(), expectedCSV, "Should have output only the header");
    }

    @Test
    public void testOutputsSummedSalesInDescendingOrderWithTopsalesmanFlagged() {
        chocolateContest.processSale("JEREMIAH_SMITH", 1);
        chocolateContest.processSale("JOE_SMITH", 1);
        chocolateContest.processSale("JANE_SMITH", 1);
        chocolateContest.processSale("JANE_SMITH", 1);
        chocolateContest.processSale("JOE_SMITH", 1);
        chocolateContest.processSale("JOE_SMITH", 1);
        final String expectedTable = "Salesman,Candy Bars Sold,Top Salesman" + EOL +
                "JOE_SMITH,3,Y" + EOL +
                "JANE_SMITH,2,N" + EOL +
                "JEREMIAH_SMITH,1,N";
        assertEquals(reportAsString(chocolateContest), expectedTable, "Output incorrect ranking");
    }

    @Test
    public void testReportHasNoTopSalesmanIfTie() {
        // implicitly tests tie ordering policy (alphabetic)
        chocolateContest.processSale("JEREMIAH_SMITH",1 );
        chocolateContest.processSale("JOE_SMITH", 1);
        chocolateContest.processSale("JANE_SMITH", 1);
        chocolateContest.processSale("JANE_SMITH", 1);
        chocolateContest.processSale("JOE_SMITH", 1);
        chocolateContest.processSale("JOE_SMITH", 1);
        chocolateContest.processSale("JANE_SMITH", 1);
        final String expectedTable = "Salesman,Candy Bars Sold,Top Salesman" + EOL +
                "JANE_SMITH,3,N" + EOL +
                "JOE_SMITH,3,N" + EOL +
                "JEREMIAH_SMITH,1,N";
        assertEquals(reportAsString(chocolateContest), expectedTable, "Output incorrect report");
    }

    /**
     * Used to print the candy contest report as a string.
     * @return String interpretation of the report.
     */
    private String reportAsString(ChocolateContest thisChocolateContest) {
        final StringWriter stringWriter = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(stringWriter);
        thisChocolateContest.printSalesRanking(printWriter);
        printWriter.flush();
        System.out.println(thisChocolateContest.hashCode());
        return stringWriter.toString().trim();
    }

}
