package filter.statistics;

import com.jcabi.github.Coordinates;
import com.jcabi.github.Github;
import com.jcabi.github.RepositoryStatistics;
import com.jcabi.github.RtGithub;
import com.jcabi.log.Logger;
import filter.Statistics;
import filter.csv.CSVCell;
import java.io.IOException;
import java.time.Duration;
import java.time.ZonedDateTime;
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
                .Smart(this.github().repos().get(this.coordinates));
            return Arrays.asList(
                new CSVCell("GitHub Forks", stat.forks()),
                new CSVCell("GitHub Stars", stat.stargazers()),
                new CSVCell("GitHub Open Issues", stat.openIssues()),
                new CSVCell("GitHub Size (KB)", stat.size()),
                new CSVCell(
                    "GitHub Age (ms)",
                    Duration.between(stat.created(), ZonedDateTime.now()).toMillis()
                )
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

    private Github github() {
        final Github result;
        final String token = System.getenv("GITHUB_TOKEN");
        if (token != null) {
            Logger.info(this, "Use token for making github requests");
            result = new RtGithub(token);
        } else {
            Logger.warn(
                this,
                "Use default github WITHOUT token! Please, pay attention to this, since it could lead to crashes"
            );
            result = new RtGithub();
        }

        return result;
    }
}
