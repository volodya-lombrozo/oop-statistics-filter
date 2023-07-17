package filter.statistics;

import filter.Application;
import filter.CSV;
import filter.Statistics;
import filter.StatisticsCase;
import filter.app.LocalApplication;
import filter.csv.CSVRows;
import filter.csv.LocalCSV;
import filter.csv.ParsedCSVRow;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class StatisticsCaseOld implements StatisticsCase {

    private final String title;
    private final CSV csv;
    private final Application project;
    private final String[] filters;

    public StatisticsCaseOld(
        final String title,
        final String csv,
        final String project,
        final String... filters
    ) {
        this(title, new LocalCSV(csv), new LocalApplication(project), filters);
    }

    public StatisticsCaseOld(
        final String title,
        final CSV csv,
        final Application project,
        final String... filters
    ) {
        this.title = title;
        this.csv = csv;
        this.project = project;
        this.filters = filters;
    }

    @Override
    public String title() {
        return this.title;
    }

    @Override
    public Statistics statistics() {
        final Set<ParsedCSVRow> csvRows = new CSVRows(this.csv, this.filters).toSet();
        final Map<String, Boolean> methods = this.methods();
        List<StatisticsOld> all = new ArrayList<>(csvRows.size());
        for (final ParsedCSVRow row : csvRows) {
            final long total = row.getCount();
            final double time = row.getOwnTime();
            if (row.isConstructor()) {
                all.add(StatisticsOld.constructor(total, time));
            } else if (!methods.containsKey(row.shortMethodName())) {
                final String alternative = row.shortMethodNameWithoutFQNForParameters();
                if (methods.get(alternative) != null) {
                    if (methods.get(alternative).booleanValue()) {
                        all.add(StatisticsOld.staticMethod(total, time));
                    } else {
                        all.add(StatisticsOld.instanceMethod(total, time));
                    }
                } else {
                    Logger.getLogger("PARSER")
                        .warning(
                            () -> String.format("Method not found: %s", row.fullMethodName())
                        );
                    all.add(StatisticsOld.notFound(total, time));
                }
            } else if (methods.get(row.shortMethodName()).booleanValue()) {
                all.add(StatisticsOld.staticMethod(total, time));
            } else {
                all.add(StatisticsOld.instanceMethod(total, time));
            }
        }
        return all.stream().reduce(
            StatisticsOld.empty(),
            (reducer, next) -> (StatisticsOld) reducer.sum(next)
        );
    }

    /**
     * Parses methods from entire project.
     * @return Map of methods.
     */
    private Map<String, Boolean> methods() {
        final Map<String, ParsedClass> all = this.parseClasses();
        return all.values().stream().flatMap(parsed -> parsed.methods().stream())
            .collect(Collectors.toMap(ParsedMethod::name, ParsedMethod::isStatic, (a, b) -> a));
    }

    private Map<String, ParsedClass> parseClasses() {
        try (final Stream<Path> files = Files.walk(this.project.path())) {
            return files
                .filter(Files::exists)
                .filter(Files::isRegularFile)
                .parallel()
                .filter(path -> path.toString().endsWith(".java"))
                .flatMap(ParsedClass::parse)
                .collect(
                    Collectors.toMap(
                        ParsedClass::name,
                        parsed -> parsed,
                        ParsedClass::add
                    )
                );
        } catch (final IOException exception) {
            throw new IllegalStateException(exception);
        }
    }
}
