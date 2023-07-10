package filter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class ReportNew {

    private final Collection<StatisticsCaseNew> cases;
    private final String filename;

    ReportNew(final String filename, final StatisticsCaseNew... all) {
        this(Arrays.asList(all), filename);
    }

    ReportNew(final StatisticsCaseNew... all) {
        this(Arrays.asList(all));
    }

    ReportNew(final Collection<StatisticsCaseNew> all) {
        this(all, "report.csv");
    }

    ReportNew(final Collection<StatisticsCaseNew> all, final String filename) {
        this.cases = all;
        this.filename = filename;
    }

    void make() throws IOException {
//        final FileWriter appendable = new FileWriter(this.filename);
//        final CSVPrinter printer = new CSVPrinter(
//            appendable,
//            CSVFormat.RFC4180.withHeader(StatisticsNew.headers())
//        );
//        StatisticsNew total = new StatisticsNew();
//        for (final StatisticsCaseNew statisticsCase : this.cases) {
//            final StatisticsNew statistics = statisticsCase.statistics();
//            System.out.println(statistics);
//            total = total.add(statistics);
//            final String title = statisticsCase.title();
//            printer.printRecord(statistics.csvRow(title));
//        }
//        System.out.println("Total:");
//        System.out.println(total);
//        printer.printRecord(total.csvRow("Total"));
//        appendable.flush();
//        appendable.close();
    }

}
