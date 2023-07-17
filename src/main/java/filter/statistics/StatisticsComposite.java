package filter.statistics;

import filter.Statistics;
import java.util.Arrays;
import java.util.stream.Stream;

public class StatisticsComposite implements Statistics {

    private final StatisticsWithModifiers application;
    private final StatisticsWithoutSources rest;

    StatisticsComposite(
        final StatisticsWithModifiers application,
        final StatisticsWithoutSources rest
    ) {
        this.application = application;
        this.rest = rest;
    }

    @Override
    public Statistics sum(final Statistics statistics) {
        StatisticsComposite other = (StatisticsComposite) statistics;
        return new StatisticsComposite(
            (StatisticsWithModifiers) other.application.sum(this.application),
            (StatisticsWithoutSources) other.rest.sum(this.rest)
        );
    }

    @Override
    public Object[] csvRow(final String title) {
        return Stream.concat(
            Arrays.stream(this.application.csvRow(title)),
            Arrays.stream(this.rest.csvRow(title))
        ).toArray(Object[]::new);
    }

    @Override
    public String[] headers() {
        return Stream.concat(
            Arrays.stream(this.application.headers()),
            Arrays.stream(this.rest.headers())
        ).toArray(String[]::new);
    }

    @Override
    public String toString() {
        return String.format("StatisticsComposite{application=%s, rest=%s}",
            this.application,
            this.rest
        );
    }
}
