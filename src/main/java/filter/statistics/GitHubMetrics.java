package filter.statistics;

import com.jcabi.github.Coordinates;
import com.jcabi.github.Github;
import com.jcabi.github.Repo;
import com.jcabi.github.Repos;
import com.jcabi.github.RtGithub;
import com.jcabi.github.Stars;
import filter.Statistics;
import filter.csv.CSVCell;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import org.cactoos.scalar.Unchecked;

public class GitHubMetrics implements Statistics {

    private final URL repo;

    public GitHubMetrics(final String url) {
        this(new Unchecked<>(() -> new URL(url)).value());
    }

    public GitHubMetrics(final URL url) {
        this.repo = url;
    }

    @Override
    public List<CSVCell> cells() {
        final Repos repos = new RtGithub().repos();
        final Repo.Smart smart = new Repo.Smart(
            repos.get(new Coordinates.Simple("apache", "tomcat")));
        final Stars stars = smart.stars();
        return Arrays.asList(
            new CSVCell("", 10)
        );
    }
}
