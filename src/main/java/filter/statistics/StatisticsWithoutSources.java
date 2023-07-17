package filter.statistics;

import filter.Statistics;
import filter.csv.CSVCell;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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

    @Override
    public List<CSVCell> cells() {
        return Arrays.asList(
            new CSVCell("Library Total", this.total()),
            new CSVCell("Library Methods", this.methods()),
            new CSVCell("Library Constructors", this.constructors())
        );
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
