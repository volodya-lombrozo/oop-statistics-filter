package filter;

import filter.csv.CSVCell;
import filter.csv.CSVOutput;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

class Report {

    private final Collection<StatisticsCase> cases;
    private final CSVOutput output;

    Report(final String filename, final StatisticsCase... all) {
        this(Arrays.asList(all), filename);
    }

    private Report(final Collection<StatisticsCase> all, final String filename) {
        this.cases = all;
        this.output = new CSVOutput(filename);
    }

    void make() throws IOException {
        final List<List<CSVCell>> table = this.cases.stream()
            .map(StatisticsCase::cells)
            .collect(Collectors.toList());
        this.output.print(Report.headers(table));
        table.stream().map(Report::values).forEach(this.output::print);
        this.output.close();
    }

    private static List<String> headers(List<? extends List<CSVCell>> table) {
        return table.stream()
            .findFirst()
            .orElseThrow(
                () -> new IllegalStateException(String.format("No data found in table %s", table))
            ).stream()
            .map(CSVCell::header)
            .collect(Collectors.toList());
    }

    private static List<Object> values(List<? extends CSVCell> cells) {
        return cells.stream()
            .map(CSVCell::value)
            .collect(Collectors.toList());
    }
}
