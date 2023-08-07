package filter;

import filter.csv.CSVLocal;
import filter.csv.CorrelationCSV;

public class Correlation {
    public static void main(String[] args) {
        new CorrelationCSV(new CSVLocal("libraries-new.csv")).report();
    }
}
