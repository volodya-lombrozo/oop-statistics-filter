package filter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

final class ReportOld {

    private final Collection<StatisticsCaseOld> cases;
    private final String filename;

    ReportOld(final String filename, final StatisticsCaseOld... all) {
        this(Arrays.asList(all), filename);
    }

    ReportOld(final StatisticsCaseOld... all) {
        this(Arrays.asList(all));
    }

    ReportOld(final Collection<StatisticsCaseOld> all) {
        this(all, "report.csv");
    }

    ReportOld(final Collection<StatisticsCaseOld> all, final String filename) {
        this.cases = all;
        this.filename = filename;
    }

    void make() throws IOException {
        final FileWriter appendable = new FileWriter(this.filename);
        CSVPrinter printer = null;
        Statistics total = StatisticsOld.empty();
        for (final StatisticsCaseOld statisticsCase : this.cases) {
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
