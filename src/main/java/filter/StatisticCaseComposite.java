package filter;

import java.util.Arrays;

public class StatisticCaseComposite implements StatisticsCase {

    private final String title;
    private final CSV csv;
    private final Application project;
    private final String[] filters;

    StatisticCaseComposite(
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
            new StatisticsCaseWithModifiers(this.title, this.csv, this.project, this.filters).statistics(),
            new StatisticsCaseWithoutSources(this.title, this.csv, this.filters).statistics()
        );
    }

    @Override
    public String toString() {
        return "StatisticCaseComposite{" +
            "title='" + title + '\'' +
            ", csv=" + csv +
            ", project=" + project +
            ", filters=" + Arrays.toString(filters) +
            '}';
    }
}
