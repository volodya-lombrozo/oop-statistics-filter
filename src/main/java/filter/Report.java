package filter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

final class Report {

    private final Collection<StatisticsCase> cases;
    private final String filename;

    Report(final String filename, final StatisticsCase... all) {
        this(Arrays.asList(all), filename);
    }

    private Report(final Collection<StatisticsCase> all, final String filename) {
        this.cases = all;
        this.filename = filename;
    }

    void make() throws IOException {
        final FileWriter appendable = new FileWriter(this.filename);
        CSVPrinter printer = null;
        Statistics total = null;
        for (final StatisticsCase statisticsCase : this.cases) {
            final Statistics statistics = statisticsCase.statistics();
            if (printer == null) {
                printer = new CSVPrinter(
                    appendable,
                    CSVFormat.RFC4180.withHeader(statistics.headers())
                );
            }
            if(total == null) {
                total = statistics;
            } else {
                total = total.sum(statistics);
            }
            System.out.println(statistics);
            final String title = statisticsCase.title();
            printer.printRecord(statistics.csvRow(title));
        }
        System.out.println("Total:");
        System.out.println(total);
        printer.printRecord(total.csvRow("Total"));
        appendable.flush();
        appendable.close();
    }
}
