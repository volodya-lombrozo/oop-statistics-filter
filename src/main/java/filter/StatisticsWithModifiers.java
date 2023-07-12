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
            "total: %d, instance private: %d, instance package-private: %d, instance public: %d, instance public overridden: %d, static private: %d, static package-private: %d, static public: %d, not found: %d, constructors: %d",
            this.total(),
            this.instancePrivate(),
            this.instancePackagePrivate(),
            this.instancePublic(),
            this.instancePublicOverridden(),
            this.staticPrivate(),
            this.staticPackagePrivate(),
            this.staticPublic(),
            this.notFound(),
            this.constructors()
        );
    }
}
