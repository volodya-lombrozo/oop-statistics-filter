package filter.csv;

import filter.CSV;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import static java.math.RoundingMode.HALF_UP;

public class CorrelationCSV {

    private final CSV csv;

    private final Map<String, List<Double>> columns;

    private final Map<String, Map<String, Double>> pearson;

    public CorrelationCSV(final CSV csv) {
        this.csv = csv;
        this.columns = new HashMap<>();
        this.pearson = new HashMap<>();
    }

    public void report() {
        try {
            final CSVParser parse = CSVFormat.DEFAULT.parse(csv.reader());
            final List<CSVRecord> records = parse.getRecords();
            final List<String> headers = records.get(0)
                .stream()
                .sorted()
                .collect(Collectors.toList());
            for (int i = 1; i < records.size(); i++) {
                final CSVRecord record = records.get(i);
                for (int j = 1; j < record.size(); j++) {
                    final double value = Double.parseDouble(record.get(j));
                    final String header = headers.get(j);
                    this.columns.putIfAbsent(header, new ArrayList<>());
                    this.columns.get(header).add(value);
                }
            }
            this.calculatePearsonCorrelation();
            this.printPearsonCorrelation();
        } catch (IOException ex) {
            throw new IllegalStateException("Can't count correlation", ex);
        }
    }

    private void printPearsonCorrelation() {
        final CSVOutput output = new CSVOutput("correlation.csv");
        final List<String> columnHeaders = this.pearson.keySet().stream()
            .sorted().collect(Collectors.toList());
        final List<String> leftColumnHeader = this.pearson.values().iterator().next().keySet()
            .stream().sorted().collect(Collectors.toList());

        List<String> headers = new ArrayList<>();
        headers.add("N/A");
        headers.addAll(columnHeaders);
        output.print(headers);

        for (final String rowHeader : leftColumnHeader) {
            List<Object> row = new ArrayList<>();
            row.add(rowHeader);
            for (final String columnHeader : columnHeaders) {
                final Double value = this.pearson.get(columnHeader).get(rowHeader);
                BigDecimal bd = BigDecimal.valueOf(value);
                bd = bd.setScale(2, HALF_UP);
                row.add(bd.doubleValue());
            }
            output.print(row);
        }
        try {
            output.close();
        } catch (final IOException ex) {
            throw new IllegalStateException(
                "Something strange is happened with closing output CSV",
                ex
            );
        }
    }

    private void calculatePearsonCorrelation() {
        final List<String> headers = new ArrayList<>(this.columns.keySet());
        for (int i = 0; i < headers.size(); i++) {
            final String first = headers.get(i);
            if (!first.contains("GitHub")) {
                continue;
            }
            for (int j = 0; j < headers.size(); j++) {
                final String second = headers.get(j);
                if (second.contains("GitHub") || second.contains("%")) {
                    continue;
                }
                final double correlation = this.correlation(
                    this.columns.get(first),
                    this.columns.get(second)
                );
                setPearsonCell(first, second, correlation);
                System.out.format(
                    "'%s' <-------> '%s' ========== %.2f%n",
                    first,
                    second,
                    correlation
                );
            }
            System.out.println("__________________________________");
        }
    }

    private double correlation(List<Double> first, List<Double> second) {
        return new PearsonsCorrelation().correlation(
            first.stream().mapToDouble(a -> a).toArray(),
            second.stream().mapToDouble(a -> a).toArray()
        );
    }

    private void setPearsonCell(final String first, final String second, final double pearson) {
        this.pearson.putIfAbsent(first, new HashMap<>());
        final Map<String, Double> column = this.pearson.get(first);
        column.put(second, pearson);
    }

}
