package jar;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class Report {

    private final Collection<StatisticsCase> cases;

    public Report(StatisticsCase... cases) {
        this(Arrays.asList(cases));
    }

    public Report(final Collection<StatisticsCase> cases) {
        this.cases = cases;
    }

    public void make() throws IOException {
        final FileWriter appendable = new FileWriter("report.csv");
        final CSVPrinter printer = new CSVPrinter(
            appendable,
            CSVFormat.RFC4180.withHeader(Statistics.headers())
        );
        for (final StatisticsCase statisticsCase : cases) {
            final Statistics statistics = statisticsCase.statistics();
            System.out.println(statistics);
            final String title = statisticsCase.title();
            printer.printRecord(statistics.csvRow(title));
        }
        appendable.flush();
        appendable.close();
    }


}
