package filter.csv;

import filter.CSV;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

public class CorrelationCSV {

    private final CSV csv;

    private Map<String, List<Double>> columns;

    public CorrelationCSV(final CSV csv) {
        this.csv = csv;
        this.columns = new HashMap<>();
    }

    public void report() {
        try {
            final CSVParser parse = CSVFormat.DEFAULT.parse(csv.reader());
            final List<CSVRecord> records = parse.getRecords();
            final List<String> headers = records.get(0).stream().collect(Collectors.toList());
            for (int i = 1; i < records.size(); i++) {
                final CSVRecord record = records.get(i);
                for (int j = 1; j < record.size(); j++) {
                    final double value = Double.parseDouble(record.get(j));
                    final String header = headers.get(j);
                    this.columns.putIfAbsent(header, new ArrayList<>());
                    this.columns.get(header).add(value);
                }
            }
            this.printCurrentColumns();
            this.calculatePearsonCorrelation();
        } catch (IOException ex) {
            throw new IllegalStateException("Can't count correlation", ex);
        }
    }

    private void printCurrentColumns() {
        for (final Map.Entry<String, List<Double>> entry : this.columns.entrySet()) {
            final String values = entry.getValue().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
            System.out.printf(
                "%s: %s%n",
                entry.getKey(),
                values
            );
        }
    }

    private void calculatePearsonCorrelation() {
        final List<String> headers = new ArrayList<>(this.columns.keySet());
        for (int i = 0; i < headers.size(); i++) {
            for (int j = i + 1; j < headers.size(); j++) {
                final String first = headers.get(i);
                final String second = headers.get(j);
                final double correlation = this.correlation(
                    this.columns.get(first),
                    this.columns.get(second)
                );
                System.out.format(
                    "'%s' <-------> '%s' ========== %.2f%n",
                    first,
                    second,
                    correlation
                );

            }
        }
    }

    private double correlation(List<Double> first, List<Double> second) {
        return new PearsonsCorrelation().correlation(
            first.stream().mapToDouble(a -> a).toArray(),
            second.stream().mapToDouble(a -> a).toArray()
        );
    }

}
