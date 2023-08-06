package filter.statistics;

import filter.Statistics;
import filter.StatisticsCase;
import filter.csv.CSVCell;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.ToString;

@ToString
public class StatisticsComposite implements Statistics {

    private final StatisticsCase application;
    private final Statistics rest;

    StatisticsComposite(
        final StatisticsCaseWithModifiers application,
        final Statistics rest
    ) {
        this.application = application;
        this.rest = rest;
    }

    @Override
    public List<CSVCell> cells() {
        return Stream.concat(
            this.application.cells().stream(),
            this.rest.cells().stream()
        ).collect(Collectors.toList());
    }
}
