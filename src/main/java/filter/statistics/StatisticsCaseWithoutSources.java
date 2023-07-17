package filter.statistics;

import filter.CSV;
import filter.StatisticsCase;
import filter.csv.CSVCell;
import filter.csv.CSVRows;
import filter.csv.ParsedCSVRow;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StatisticsCaseWithoutSources implements StatisticsCase {

    private static final String[] EMPTY = {};
    private final String title;
    private final CSV csv;
    private final String[] excluded;

    StatisticsCaseWithoutSources(
        final String title,
        final CSV csv,
        final String... excluded
    ) {
        this.title = title;
        this.csv = csv;
        this.excluded = excluded;
    }

    @Override
    public String title() {
        return this.title;
    }

    @Override
    public StatisticsWithoutSources statistics() {
        final Set<ParsedCSVRow> rows = new CSVRows(this.csv, StatisticsCaseWithoutSources.EMPTY,
            this.excluded
        ).toSet();
        final StatisticsWithoutSources stat = new StatisticsWithoutSources();
        for (final ParsedCSVRow row : rows) {
            if (row.isConstructor()) {
                stat.add(new MethodStatistics(row.getCount(), Modifier.CONSTRUCTOR));
            } else {
                stat.add(new MethodStatistics(row.getCount()));
            }
        }
        return stat;
    }

    @Override
    public List<CSVCell> cells() {
        return Stream.concat(
            Stream.of(new CSVCell("Application", this.title)),
            this.statistics().cells().stream()
        ).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format("StatisticsCaseWithoutSources{title='%s', csv=%s, excluded=%s}",
            this.title,
            this.csv,
            Arrays.toString(this.excluded)
        );
    }
}
