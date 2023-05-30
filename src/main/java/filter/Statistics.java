package filter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Statistics {

    final long totalMethods;
    final long staticMethods;
    final long instanceMethods;
    final long constructors;
    final double totalTime;
    final double staticTime;
    final double instanceTime;

    final double constructorTime;


    public Statistics(
        final long totalMethods,
        final long staticMethods,
        final long instanceMethods,
        final long constructors,
        final double totalTime,
        final double staticTime,
        final double instanceTime,
        final double constructorTime
    ) {
        this.totalMethods = totalMethods;
        this.staticMethods = staticMethods;
        this.instanceMethods = instanceMethods;
        this.constructors = constructors;
        this.totalTime = totalTime;
        this.staticTime = staticTime;
        this.instanceTime = instanceTime;
        this.constructorTime = constructorTime;
    }

    public static Statistics empty() {
        return new Statistics(0, 0, 0, 0, 0, 0, 0, 0);
    }

    public static Statistics notFound(final long total, final double time) {
        return new Statistics(total, 0, 0, 0, time, 0, 0, 0);
    }

    public static Statistics staticMethod(final long total, final double time) {
        return new Statistics(total, total, 0, 0, time, time, 0, 0);
    }

    public static Statistics instanceMethod(final long total, final double time) {
        return new Statistics(total, 0, total, 0, time, 0, time, 0);
    }

    public static Statistics constructor(final long total, final double time) {
        return new Statistics(total, 0, 0, total, time, 0, 0, time);
    }

    private long totalNotFound() {
        return this.totalMethods - this.constructors - this.instanceMethods - this.staticMethods;
    }

    public String pStatic() {
        return this.percent(
            (double) this.staticMethods / this.totalMethods
        );
    }

    public String pInstance() {
        return this.percent(
            (double) this.instanceMethods / this.totalMethods
        );
    }

    public String pNotFound() {
        return this.percent(
            (double) this.totalNotFound() / this.totalMethods
        );
    }

    public String pDirtyStatic() {
        return this.percent(
            (double) (this.staticMethods + this.totalNotFound()) / this.totalMethods
        );
    }

    public String pDirtyInstance() {
        return this.percent(
            (double) (this.instanceMethods + this.totalNotFound()) / this.totalMethods
        );
    }

    public String pConstructors() {
        return this.percent(
            (double) this.constructors / this.totalMethods
        );
    }

    private double time(double value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private String percent(double value) {
        return String.format("%.2f", value * 100);
    }

    Statistics sum(Statistics other) {
        return new Statistics(
            this.totalMethods + other.totalMethods,
            this.staticMethods + other.staticMethods,
            this.instanceMethods + other.instanceMethods,
            this.constructors + other.constructors,
            this.totalTime + other.totalTime,
            this.staticTime + other.staticTime,
            this.instanceTime + other.instanceTime,
            this.constructorTime + other.constructorTime
        );
    }

    public static String[] headers() {
        return new String[]{
            "Application",
            "Total Methods",
            "Static Methods",
            "Instance Methods",
            "Constructors",
            "Not Found Methods (lambdas)",
            "Total Time (ms)",
            "Time in Static Methods (ms)",
            "Time in Intense Methods (ms)",
            "Time in Constructors Methods (ms)",
            "Percent of Static Methods",
            "Percent of Instance Methods",
            "Percent of Constructors",
            "Percent Not Found Methods",
            "Percent Dirty Static Methods",
            "Percent Dirty Instance Methods",
        };
    }

    public Object[] csvRow(final String title) {
        return new Object[]{
            title,
            this.totalMethods,
            this.staticMethods,
            this.instanceMethods,
            this.constructors,
            this.totalNotFound(),
            this.time(this.totalTime),
            this.time(this.staticTime),
            this.time(this.instanceTime),
            this.time(this.constructorTime),
            this.pStatic(),
            this.pInstance(),
            this.pConstructors(),
            this.pNotFound(),
            this.pDirtyStatic(),
            this.pDirtyInstance()
        };
    }

    @Override
    public String toString() {
        return "Statistics{" +
            "total=" + this.totalMethods +
            ", staticMethods=" + this.staticMethods +
            ", instanceMethods=" + this.instanceMethods +
            ", constructors=" + this.constructors +
            ", notFound=" + this.totalNotFound() +
            ", totalTime=" + this.time(this.totalTime) +
            ", staticTime=" + this.time(this.staticTime) +
            ", instanceTime=" + this.time(this.instanceTime) +
            ", constructorsTime=" + this.time(this.constructorTime) +
            ", Static Percent=" + this.pStatic() +
            ", Instance Percent=" + this.pInstance() +
            ", Constructors Percent=" + this.pConstructors() +
            ", Not Found Percent=" + this.pNotFound() +
            ", Static Dirty Percent=" + this.pDirtyStatic() +
            ", Instance Dirty Percent=" + this.pDirtyInstance() +
            '}';
    }
}
