package filter.statistics;

import filter.Statistics;
import filter.csv.CSVCell;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class StatisticsWithModifiers implements Statistics {

    private final Collection<MethodStatistics> rows;

    StatisticsWithModifiers() {
        this(new ArrayList<>());
    }

    private StatisticsWithModifiers(final Collection<MethodStatistics> rows) {
        this.rows = rows;
    }

    @Override
    public List<CSVCell> cells() {
        return Arrays.asList(
            new CSVCell("Total", this.total()),
            new CSVCell("Instance Private Methods", this.instancePrivate()),
            new CSVCell("Instance Package-Private Methods", this.instancePackagePrivate()),
            new CSVCell("Instance Protected", this.instanceProtected()),
            new CSVCell("Instance Protected Overridden Methods",
                this.instanceProtectedOverridden()
            ),
            new CSVCell("Instance Public Methods", this.instancePublic()),
            new CSVCell("Instance Public Overridden Methods", this.instancePublicOverridden()),
            new CSVCell("Static Private Methods", this.staticPrivate()),
            new CSVCell("Static Package-Private Methods", this.staticPackagePrivate()),
            new CSVCell("Static Protected", this.staticProtected()),
            new CSVCell("Static Public Methods", this.staticPublic()),
            new CSVCell("Not Found Methods", this.notFound()),
            new CSVCell("Constructors", this.constructors()),
            new CSVCell("Instance Private Methods, %", this.instancePrivatePercent()),
            new CSVCell("Instance Package-Private Methods, %",
                this.instancePackagePrivatePercent()
            ),
            new CSVCell("Instance Protected Methods, %", this.instanceProctectedPercent()),
            new CSVCell("Instance Protected Overridden, %", this.instanceProtectedOverriddenPercent()),
            new CSVCell("Instance Public Methods, %", this.instancePublicPercent()),
            new CSVCell("Instance Public Overridden Methods, %",
                this.instancePublicOverriddenPercent()
            ),
            new CSVCell("Static Private Methods, %", this.staticPrivatePercent()),
            new CSVCell("Static Package-Private Methods, %", this.staticPackagePrivatePercent()),
            new CSVCell("Static Protected, %", this.staticProtectedPercent()),
            new CSVCell("Static Public Methods, %", this.staticPublicPercent()),
            new CSVCell("Not Found Methods, %", this.notFoundPercent()),
            new CSVCell("Constructors, %", this.constructorsPercent())
        );
    }

    StatisticsWithModifiers add(final MethodStatistics statistics) {
        this.rows.add(statistics);
        return this;
    }

    private long total() {
        return this.rows.stream().mapToLong(MethodStatistics::count).sum();
    }

    private long notFound() {
        return this.rows.stream()
            .filter(MethodStatistics::isNotFound)
            .mapToLong(MethodStatistics::count).sum();
    }

    private long instancePrivate() {
        return this.rows.stream()
            .filter(MethodStatistics::isInstancePrivate)
            .mapToLong(MethodStatistics::count).sum();
    }

    private long instancePackagePrivate() {
        return this.rows.stream()
            .filter(MethodStatistics::isInstancePackagePrivate)
            .mapToLong(MethodStatistics::count)
            .sum();
    }

    private String instanceProctectedPercent() {
        return StatisticsWithModifiers.percent(this.instanceProtected() / (double) this.total());
    }

    private long instanceProtected() {
        return this.rows.stream()
            .filter(MethodStatistics::isInstanceProtected)
            .mapToLong(MethodStatistics::count)
            .sum();
    }

    private long instanceProtectedOverridden() {
        return this.rows.stream()
            .filter(MethodStatistics::isInstanceProtectedOverridden)
            .mapToLong(MethodStatistics::count)
            .sum();
    }

    private long instancePublic() {
        return this.rows.stream()
            .filter(MethodStatistics::isInstancePublic)
            .mapToLong(MethodStatistics::count)
            .sum();
    }

    private long instancePublicOverridden() {
        return this.rows.stream()
            .filter(MethodStatistics::isInstancePublicOverridden)
            .mapToLong(MethodStatistics::count)
            .sum();
    }

    private long staticPrivate() {
        return this.rows.stream()
            .filter(MethodStatistics::isStaticPrivate)
            .mapToLong(MethodStatistics::count)
            .sum();
    }

    private long staticPackagePrivate() {
        return this.rows.stream()
            .filter(MethodStatistics::isStaticPackagePrivate)
            .mapToLong(MethodStatistics::count)
            .sum();
    }

    private long staticProtected() {
        return this.rows.stream()
            .filter(MethodStatistics::isStaticProtected)
            .mapToLong(MethodStatistics::count)
            .sum();
    }

    private long staticPublic() {
        return this.rows.stream()
            .filter(MethodStatistics::isStaticPublic)
            .mapToLong(MethodStatistics::count)
            .sum();
    }

    private long constructors() {
        return this.rows.stream()
            .filter(MethodStatistics::isConstructor)
            .mapToLong(MethodStatistics::count)
            .sum();
    }

    @Override
    public String toString() {
        return String.format(
            "Total: %d, Instance Private Methods: %d, Instance Package-Private Methods: %d, Instance Public Methods: %d, Instance Public Overridden Methods: %d, Static Private Methods: %d, Static Package-Private Methods: %d, Static Public Methods: %d, Not Found Methods: %d, Constructors: %d, Instance Private Methods, %%: %s, Instance Package-Private Methods, %%: %s, Instance Public Methods, %%: %s, Instance Public Overridden Methods, %%: %s, Static Private Methods, %%: %s, Static Package-Private Methods, %%: %s, Static Public Methods, %%: %s, Not Found Methods, %%: %s, Constructors, %%: %s",
            this.total(),
            this.instancePrivate(),
            this.instancePackagePrivate(),
            this.instancePublic(),
            this.instancePublicOverridden(),
            this.staticPrivate(),
            this.staticPackagePrivate(),
            this.staticPublic(),
            this.notFound(),
            this.constructors(),
            this.instancePrivatePercent(),
            this.instancePackagePrivatePercent(),
            this.instancePublicPercent(),
            this.instancePublicOverriddenPercent(),
            this.staticPrivatePercent(),
            this.staticPackagePrivatePercent(),
            this.staticPublicPercent(),
            this.notFoundPercent(),
            this.constructorsPercent()
        );
    }

    private String constructorsPercent() {
        return StatisticsWithModifiers.percent(this.constructors() / (double) this.total());
    }

    private String notFoundPercent() {
        return StatisticsWithModifiers.percent(this.notFound() / (double) this.total());
    }

    private String staticPublicPercent() {
        return StatisticsWithModifiers.percent(this.staticPublic() / (double) this.total());
    }

    private String staticPackagePrivatePercent() {
        return StatisticsWithModifiers.percent(this.staticPackagePrivate() / (double) this.total());
    }

    private String staticPrivatePercent() {
        return StatisticsWithModifiers.percent(this.staticPrivate() / (double) this.total());
    }

    private String staticProtectedPercent() {
        return StatisticsWithModifiers.percent(this.staticProtected() / (double) this.total());
    }

    private String instanceProtectedOverriddenPercent() {
        return StatisticsWithModifiers.percent(this.instanceProtectedOverridden() / (double) this.total());
    }

    private String instancePublicOverriddenPercent() {
        return StatisticsWithModifiers.percent(
            this.instancePublicOverridden() / (double) this.total());
    }

    private String instancePublicPercent() {
        return StatisticsWithModifiers.percent(this.instancePublic() / (double) this.total());
    }

    private String instancePackagePrivatePercent() {
        return StatisticsWithModifiers.percent(
            this.instancePackagePrivate() / (double) this.total());
    }

    private String instancePrivatePercent() {
        return StatisticsWithModifiers.percent(this.instancePrivate() / (double) this.total());
    }

    private static String percent(final double value) {
        return String.format("%.4f", 100 * value);
    }
}
