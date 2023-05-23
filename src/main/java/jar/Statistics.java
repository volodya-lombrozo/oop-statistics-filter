package jar;

import java.math.MathContext;
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

    @Override
    public String toString() {
        return "Statistics{" +
            "total=" + totalMethods +
            ", staticMethods=" + staticMethods +
            ", instanceMethods=" + instanceMethods +
            ", constructors=" + constructors +
            ", notFound=" + this.totalNotFound() +
            ", totalTime=" + totalTime +
            ", staticTime=" + staticTime +
            ", instanceTime=" + instanceTime +
            ", constructorsTime=" + constructorTime +
            ", Static Percent=" + this.pStatic() +
            ", Instance Percent=" + this.pInstance() +
            ", Not Found Percent=" + this.pNotFound() +
            ", Constructors Percent=" + this.pConstructors() +
            ", Static Dirty Percent=" + this.pDirtyStatic() +
            ", Instance Dirty Percent=" + this.pDirtyInstance() +
            '}';
    }
}
