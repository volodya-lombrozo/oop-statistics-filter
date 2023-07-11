package filter;

import java.util.Set;

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
}
