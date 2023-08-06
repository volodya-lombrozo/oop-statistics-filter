package filter.statistics;

import filter.Application;
import filter.CSV;
import filter.Statistics;
import filter.StatisticsCase;
import filter.csv.CSVCell;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.ToString;

@ToString
public class StatisticCaseApplications implements StatisticsCase {

    private final String title;
    private final CSV csv;
    private final Application project;
    private final String[] filters;

    public StatisticCaseApplications(
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
    public List<CSVCell> cells() {
        return Stream.concat(
            Stream.of(new CSVCell("Application", this.title)),
            this.statistics().cells().stream()
        ).collect(Collectors.toList());
    }

    private Statistics statistics() {
        return new StatisticsComposite(
            new StatisticsCaseWithModifiers(
                this.title,
                this.csv,
                this.project,
                this.filters
            ),
            new StatisticsCaseWithoutSources(
                this.title,
                this.csv,
                this.filters
            ).statistics()
        );
    }
}
