package filter.statistics;

import filter.Statistics;
import filter.csv.CSVCell;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import lombok.ToString;

@ToString
public class StatisticsWithoutSources implements Statistics {

    private final Collection<MethodStatistics> statistics;

    StatisticsWithoutSources() {
        this(new ArrayList<>(0));
    }

    private StatisticsWithoutSources(final Collection<MethodStatistics> statistics) {
        this.statistics = statistics;
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
        return this.statistics.stream().mapToLong(MethodStatistics::count).sum();
    }

    private long methods() {
        return this.statistics.stream()
            .filter(method -> !method.isConstructor())
            .mapToLong(MethodStatistics::count)
            .sum();
    }

    private long constructors() {
        return this.statistics.stream()
            .filter(MethodStatistics::isConstructor)
            .mapToLong(MethodStatistics::count)
            .sum();
    }
}
