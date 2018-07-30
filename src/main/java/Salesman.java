public class Salesman implements Comparable<Salesman> {
    private String salesmanName;
    private int barsSold;

    public Salesman(String salesmanName, int barsSold) {
        this.salesmanName = salesmanName;
        this.barsSold = barsSold;
    }

    @Override
    public String toString() {
        return String.format("Salesman: %s - %d", salesmanName, barsSold);
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public int getBarsSold() {
        return barsSold;
    }

    public void sellBars(int bars) {
        barsSold += bars;
    }

    public int compareTo(Salesman other) {
        int barsDiff = barsSold - other.barsSold;
        if (barsDiff == 0) {
            return salesmanName.compareTo(other.salesmanName);
        }
        return barsDiff;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Salesman)) {
            return false;
        }

        Salesman other = (Salesman) obj;
        return salesmanName.equals(other.salesmanName);
    }
}