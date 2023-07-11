package filter;

public class StatisticsCaseWithoutSources implements StatisticsCase {

    private static final String[] EMPTY = {};
    private final String title;
    private final CSV csv;
    private final String[] excluded;

    public StatisticsCaseWithoutSources(
        final String title,
        final CSV csv,
        final String[] excluded
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
    public Statistics statistics() {
        new CSVRows(this.csv, StatisticsCaseWithoutSources.EMPTY, this.excluded).toSet();
        return null;
    }
}
