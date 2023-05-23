package jar;

public class Statistics {

    final long total;
    final long staticMethods;
    final long instanceMethods;
    final long constructors;

    public Statistics(
        final long total,
        final long staticMethods,
        final long instanceMethods,
        final long constructors
    ) {
        this.total = total;
        this.staticMethods = staticMethods;
        this.instanceMethods = instanceMethods;
        this.constructors = constructors;
    }

    public static Statistics empty() {
        return new Statistics(0, 0, 0, 0);
    }

    public static Statistics notFound(final long total) {
        return new Statistics(total, 0, 0, 0);
    }

    public static Statistics staticMethod(final long total) {
        return new Statistics(total, total, 0, 0);
    }

    public static Statistics instanceMethod(final long total) {
        return new Statistics(total, 0, total, 0);
    }

    public static Statistics constructor(final long total) {
        return new Statistics(total, 0, 0, total);
    }


    private long totalNotFound() {
        return this.total - this.constructors - this.instanceMethods - this.staticMethods;
    }

    Statistics sum(Statistics other) {
        return new Statistics(
            this.total + other.total,
            this.staticMethods + other.staticMethods,
            this.instanceMethods + other.instanceMethods,
            this.constructors + other.constructors
        );
    }

    @Override
    public String toString() {
        return "Statistics{" +
            "total=" + total +
            ", staticMethods=" + staticMethods +
            ", instanceMethods=" + instanceMethods +
            ", constructors=" + constructors +
            ", notFound=" + this.totalNotFound() +
            '}';
    }
}
