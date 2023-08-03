package filter.statistics;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

class GitHubMetricsTest {

    @Test
    void gathersAllTheRequiredMetrics() {
        MatcherAssert.assertThat(
            "Metrics are gathered",
            new GitHubMetrics(
                "https://github.com/apache/tomcat.git").cells(),
            Matchers.not(Matchers.empty())
        );
    }
}