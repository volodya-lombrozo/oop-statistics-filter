package filter.statistics;

import filter.Application;
import filter.CSV;
import filter.Statistics;
import filter.StatisticsCase;
import filter.statistics.StatisticsCaseWithModifiers;
import filter.statistics.StatisticsCaseWithoutSources;
import filter.statistics.StatisticsComposite;
import java.util.Arrays;

public class StatisticCaseComposite implements StatisticsCase {

    private final String title;
    private final CSV csv;
    private final Application project;
    private final String[] filters;

    public StatisticCaseComposite(
        final String title,
        final CSV csv,
        final Application project,
        final String... filters
    ) {
        this.title = title;
        this.csv = csv;
        this.project = project;
        this.filters = filters;
    }

    @Override
    public String title() {
        return this.title;
    }

    @Override
    public Statistics statistics() {
        return new StatisticsComposite(
            new StatisticsCaseWithModifiers(
                this.title,
                this.csv,
                this.project,
                this.filters
            ).statistics(),
            new StatisticsCaseWithoutSources(
                this.title,
                this.csv,
                this.filters
            ).statistics()
        );
    }

    @Override
    public String toString() {
        return String.format("StatisticCaseComposite{title='%s', csv=%s, project=%s, filters=%s}",
            this.title, this.csv, this.project, Arrays.toString(this.filters)
        );
    }
}
