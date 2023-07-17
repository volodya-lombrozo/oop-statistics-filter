package filter.statistics;

import filter.StatisticsCase;
import filter.csv.CSVCell;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class SoftMetrics implements StatisticsCase {

    private final long loc;
    private final LocalDate birth;
    private final long contributors;
    private final long stars;

    public SoftMetrics(
        final long loc,
        final LocalDate birth,
        final long contributors,
        final long stars
    ) {
        this.loc = loc;
        this.birth = birth;
        this.contributors = contributors;
        this.stars = stars;
    }

    @Override
    public List<CSVCell> cells() {
        return Arrays.asList(
            new CSVCell("Lines of code", this.loc),
            new CSVCell("Age (Days)", Duration.between(this.birth, LocalDate.now()).toDays()),
            new CSVCell("Contributors", this.contributors),
            new CSVCell("Stars", this.stars)
        );
    }
}
