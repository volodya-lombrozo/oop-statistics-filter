package filter.statistics;

import filter.Application;
import filter.CSV;
import filter.StatisticsCase;
import filter.csv.CSVCell;
import java.util.List;
import lombok.ToString;

@ToString
public class StatisticsCaseLibrary implements StatisticsCase {

    private final String title;
    private final CSV csv;
    private final Application project;
    private final String[] filters;

    public StatisticsCaseLibrary(
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
        return new StatisticsComposite(
            new StatisticsCaseWithModifiers(title, csv, project, filters),
            new GitHubMetrics(this.project.githubUrl())
        ).cells();
    }
}
