package filter.statistics;

import com.jcabi.github.Coordinates;
import com.jcabi.github.RepositoryStatistics;
import com.jcabi.github.RtGithub;
import filter.Statistics;
import filter.csv.CSVCell;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GitHubMetrics implements Statistics {

    private final Coordinates coordinates;

    public GitHubMetrics(final String url) {
        this(new Coordinates.Https(url));
    }

    public GitHubMetrics(final Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public List<CSVCell> cells() {
        try {
            final RepositoryStatistics.Smart stat = new RepositoryStatistics
                .Smart(new RtGithub().repos().get(this.coordinates));
            return Arrays.asList(
                new CSVCell("GitHub Forks", stat.forks()),
                new CSVCell("GitHub Issues", stat.openIssues()),
                new CSVCell("GitHub Stargazers", stat.stargazers()),
                new CSVCell("GitHub Watchers", stat.watchers()),
                new CSVCell("GitHub Size", stat.size())
            );
        } catch (final IOException ex) {
            throw new IllegalStateException(
                String.format(
                    "Can't get statistics from from repo %s",
                    this.coordinates
                ),
                ex
            );
        }
    }
}
