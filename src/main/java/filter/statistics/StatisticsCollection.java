package filter.statistics;

import filter.StatisticsCase;
import filter.csv.CSVCell;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class StatisticsCollection implements StatisticsCase {

    private final Collection<? extends StatisticsCase> all;

    public StatisticsCollection(final StatisticsCase... all) {
        this(Arrays.asList(all));
    }

    private StatisticsCollection(final Collection<? extends StatisticsCase> all) {
        this.all = all;
    }

    @Override
    public List<CSVCell> cells() {
        return this.all.stream()
            .map(StatisticsCase::cells)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }
}
