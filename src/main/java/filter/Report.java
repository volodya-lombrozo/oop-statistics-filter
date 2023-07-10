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

    Report(final StatisticsCase... all) {
        this(Arrays.asList(all));
    }

    Report(final Collection<StatisticsCase> all) {
        this(all, "report.csv");
    }

    Report(final Collection<StatisticsCase> all, final String filename) {
        this.cases = all;
        this.filename = filename;
    }

    void make() throws IOException {
        final FileWriter appendable = new FileWriter(this.filename);
        CSVPrinter printer = null;
        Statistics total = StatisticsOld.empty();
        for (final StatisticsCase statisticsCase : this.cases) {
            final Statistics statistics = statisticsCase.statistics();
            if(printer == null){
                printer = new CSVPrinter(
                    appendable,
                    CSVFormat.RFC4180.withHeader(statistics.headers())
                );
            }
            System.out.println(statistics);
            total = total.sum(statistics);
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
