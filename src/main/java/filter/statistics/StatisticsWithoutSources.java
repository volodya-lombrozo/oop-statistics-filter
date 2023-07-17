package filter.statistics;

import filter.Statistics;
import java.util.ArrayList;
import java.util.Collection;

public class StatisticsWithoutSources implements Statistics {

    private final Collection<MethodStatistics> statistics;

    StatisticsWithoutSources() {
        this(new ArrayList<>(0));
    }

    private StatisticsWithoutSources(final Collection<MethodStatistics> statistics) {
        this.statistics = statistics;
    }

    @Override
    public Statistics add(final Statistics statistics) {
        StatisticsWithoutSources other = (StatisticsWithoutSources) statistics;
        final Collection<MethodStatistics> res = new ArrayList<>(this.statistics);
        res.addAll(other.statistics);
        return new StatisticsWithoutSources(res);
    }

    @Override
    public Object[] csvRow(final String title) {
        return new Object[]{
            title,
            this.total(),
            this.methods(),
            this.constructors(),
        };
    }

    @Override
    public String[] headers() {
        return new String[]{
            "Application",
            "Total",
            "Methods",
            "Constructors",
        };
    }

    void add(final MethodStatistics methodStatistics) {
        this.statistics.add(methodStatistics);
    }

    private long total() {
        return this.statistics.stream().mapToLong(MethodStatistics::total).sum();
    }

    private long methods() {
        return this.statistics.stream()
            .filter(method -> !method.isConstructor())
            .mapToLong(MethodStatistics::total)
            .sum();
    }

    private long constructors() {
        return this.statistics.stream()
            .filter(MethodStatistics::isConstructor)
            .mapToLong(MethodStatistics::total)
            .sum();
    }

    @Override
    public String toString() {
        return String.format(
            "total: %d, methods: %d, constructors: %d",
            this.total(),
            this.methods(),
            this.constructors()
        );
    }
}
