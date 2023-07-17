package filter.statistics;

import filter.Statistics;
import filter.csv.CSVCell;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.ToString;

@ToString
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
    public List<CSVCell> cells() {
        return Stream.concat(
            this.application.cells().stream(),
            this.rest.cells().stream()
        ).collect(Collectors.toList());
    }
}
