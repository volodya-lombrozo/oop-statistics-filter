package filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class StatisticsWithModifiers implements Statistics {

    private final Collection<MethodStatistics> rows;

    StatisticsWithModifiers() {
        this(new ArrayList<>());
    }

    private StatisticsWithModifiers(final Collection<MethodStatistics> rows) {
        this.rows = rows;
    }

    @Override
    public Statistics sum(final Statistics original) {
        StatisticsWithModifiers other = (StatisticsWithModifiers) original;
        final List<MethodStatistics> res = new ArrayList<>(this.rows);
        res.addAll(other.rows);
        return new StatisticsWithModifiers(res);
    }

    @Override
    public String[] headers() {
        return new String[]{
            "Application",
            "Total",
            "Instance Private Methods",
            "Instance Package-Private Methods",
            "Instance Public Methods",
            "Instance Public Overridden Methods",
            "Static Private Methods",
            "Static Package-Private Methods",
            "Static Public Methods",
            "Not Found Methods",
            "Constructors",
            "Instance Private Methods, %",
            "Instance Package-Private Methods, %",
            "Instance Public Methods, %",
            "Instance Public Overridden Methods, %",
            "Static Private Methods, %",
            "Static Package-Private Methods, %",
            "Static Public Methods, %",
            "Not Found Methods, %",
            "Constructors, %",
        };
    }

    public Object[] csvRow(final String title) {
        return new Object[]{
            title,
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
            this.constructorsPercent(),
        };
    }

    StatisticsWithModifiers add(final MethodStatistics statistics) {
        this.rows.add(statistics);
        return this;
    }

    private long total() {
        return this.rows.stream().mapToLong(MethodStatistics::total).sum();
    }

    private long notFound() {
        return this.rows.stream()
            .filter(MethodStatistics::isNotFound)
            .mapToLong(MethodStatistics::total).sum();
    }

    private long instancePrivate() {
        return this.rows.stream()
            .filter(MethodStatistics::isInstancePrivate)
            .mapToLong(MethodStatistics::total).sum();
    }

    private long instancePackagePrivate() {
        return this.rows.stream()
            .filter(MethodStatistics::isInstancePackagePrivate)
            .mapToLong(MethodStatistics::total)
            .sum();
    }

    private long instancePublicOverridden() {
        return this.rows.stream()
            .filter(MethodStatistics::isInstancePublicOverridden)
            .mapToLong(MethodStatistics::total)
            .sum();
    }

    private long instancePublic() {
        return this.rows.stream()
            .filter(MethodStatistics::isInstancePublic)
            .mapToLong(MethodStatistics::total)
            .sum();
    }

    private long staticPrivate() {
        return this.rows.stream()
            .filter(MethodStatistics::isStaticPrivate)
            .mapToLong(MethodStatistics::total)
            .sum();
    }

    private long staticPackagePrivate() {
        return this.rows.stream()
            .filter(MethodStatistics::isStaticPackagePrivate)
            .mapToLong(MethodStatistics::total)
            .sum();
    }

    private long staticPublic() {
        return this.rows.stream()
            .filter(MethodStatistics::isStaticPublic)
            .mapToLong(MethodStatistics::total)
            .sum();
    }

    private long constructors() {
        return this.rows.stream()
            .filter(MethodStatistics::isConstructor)
            .mapToLong(MethodStatistics::total)
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
        return StatisticsWithModifiers.percent( this.constructors() / (double) this.total());
    }

    private String  notFoundPercent() {
        return StatisticsWithModifiers.percent( this.notFound() / (double) this.total());
    }

    private String staticPublicPercent() {
        return StatisticsWithModifiers.percent( this.staticPublic() / (double) this.total());
    }

    private String staticPackagePrivatePercent() {
        return StatisticsWithModifiers.percent( this.staticPackagePrivate() / (double) this.total());
    }

    private String staticPrivatePercent() {
        return StatisticsWithModifiers.percent(this.staticPrivate() / (double) this.total());
    }

    private String instancePublicOverriddenPercent() {
        return StatisticsWithModifiers.percent(this.instancePublicOverridden() / (double) this.total());
    }

    private String instancePublicPercent() {
        return StatisticsWithModifiers.percent(this.instancePublic() / (double) this.total());
    }

    private String  instancePackagePrivatePercent() {
        return StatisticsWithModifiers.percent( this.instancePackagePrivate() / (double) this.total());
    }

    private String instancePrivatePercent() {
        return StatisticsWithModifiers.percent(this.instancePrivate() / (double) this.total());
    }

    private static String percent(final double value) {
        return String.format("%.4f", 100 * value);
    }
}
