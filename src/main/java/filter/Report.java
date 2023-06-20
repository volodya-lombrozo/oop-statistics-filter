package filter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

final class Report {

    private final Collection<? extends StatisticsCase> cases;

    Report(final StatisticsCase... all) {
        this(Arrays.asList(all));
    }

    private Report(final Collection<? extends StatisticsCase> all) {
        this.cases = all;
    }

    void make() throws IOException {
        final FileWriter appendable = new FileWriter("report.csv");
        final CSVPrinter printer = new CSVPrinter(
            appendable,
            CSVFormat.RFC4180.withHeader(Statistics.headers())
        );
        Statistics total = Statistics.empty();
        for (final StatisticsCase statisticsCase : this.cases) {
            final Statistics statistics = statisticsCase.statistics();
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
