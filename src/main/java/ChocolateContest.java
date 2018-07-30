import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChocolateContest implements CandyContest {

    private static List<Salesman> salesmen;

    public ChocolateContest() {
        salesmen = new ArrayList<>();
    }

    public void processSale(String salesmanName, int barsSold) {
        if (salesmanName == null || salesmanName.equals("") || salesmanName.equals(" ")) {
            throw new RuntimeException("Salesman name must be provided");
        }
        int salesmanIndex = salesmen.indexOf(new Salesman(salesmanName, 0));
        if (salesmanIndex >= 0) {
            salesmen.get(salesmanIndex).sellBars(barsSold);
        } else {
            salesmen.add(new Salesman(salesmanName, barsSold));
        }
        salesmen.sort(new ReverseSortSalesmenComparator());
    }

    public String getTopSalesman() {
        if (salesmen.size() == 0) {
            return null;
        }
        Salesman firstSalesman = salesmen.get(0);
        if (salesmen.size() == 1) {
            return firstSalesman.getSalesmanName();
        }
        Salesman secondSalesman = salesmen.get(1);
        if (firstSalesman.getBarsSold() == secondSalesman.getBarsSold()) {
            return null;
        } else {
            return firstSalesman.getSalesmanName();
        }
    }

    public void printSalesRanking(PrintWriter writer) {
        writer.println("Salesman,Candy Bars Sold,Top Salesman");
        for (Salesman salesman : salesmen) {
            char isTopSalesman = 'N';
            if (salesman.getSalesmanName().equals(getTopSalesman())) {
                isTopSalesman = 'Y';
            }
            writer.printf("%s,%d,%s%n", salesman.getSalesmanName(), salesman.getBarsSold(), isTopSalesman);
        }
    }

}