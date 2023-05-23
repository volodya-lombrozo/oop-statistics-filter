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

    public static Statistics notFound() {
        return new Statistics(1, 0, 0, 0);
    }

    public static Statistics staticMethod() {
        return new Statistics(1, 1, 0, 0);
    }

    public static Statistics instanceMethod() {
        return new Statistics(1, 0, 1, 0);
    }

    public static Statistics constructor() {
        return new Statistics(1, 0, 0, 1);
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
