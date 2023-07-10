package filter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

final class StatisticsCaseWithModifiers implements StatisticsCase {

    private final String title;
    private final CSV csv;
    private final Application project;
    private final String[] filters;

    StatisticsCaseWithModifiers(
        final String title,
        final String csv,
        final String project,
        final String... filters
    ) {
        this(title, new LocalCSV(csv), new LocalApplication(project), filters);
    }

    StatisticsCaseWithModifiers(
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

    public String title() {
        return this.title;
    }

    @Override
    public StatisticsWithModifiers statistics() {
        final Set<ParsedCSVRow> csvRows = this.parseCSV();
        final Map<String, Modifiers> methods = this.methods();
        StatisticsWithModifiers stats = new StatisticsWithModifiers();
        for (final ParsedCSVRow row : csvRows) {
            final long total = row.getCount();
            if (row.isConstructor()) {
                stats.add(new MethodStatistics(total, Modifier.CONSTRUCTOR));
            } else if (!methods.containsKey(row.shortMethodName())) {
                final String alternative = row.shortMethodNameWithoutFQNForParameters();
                if (methods.containsKey(alternative)) {
                    stats.add(new MethodStatistics(total, methods.get(alternative)));
                } else {
                    Logger.getLogger("PARSER")
                        .warning(
                            () -> String.format("Method not found: %s", row.fullMethodName())
                        );
                    stats.add(new MethodStatistics(total, Modifier.NOT_FOUND));
                }
            } else if (methods.containsKey(row.shortMethodName())) {
                stats.add(new MethodStatistics(total, methods.get(row.shortMethodName())));
            }
        }
        return stats;
    }

    private Set<ParsedCSVRow> parseCSV() {
        try {
            final CSVParser parse = CSVFormat.RFC4180.withHeader(
                "Method",
                "Time (ms)",
                "Avg. Time (ms)",
                "Own Time (ms)",
                "Count"
            ).parse(this.csv.reader());
            return parse.getRecords().stream().map(ParsedCSVRow::new)
                .filter(ParsedCSVRow::isNotHeader)
                .filter(row -> row.withinPackage(this.filters[0]))
                .collect(Collectors.toSet());
        } catch (final IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * Parses methods from entire project.
     * @return Map of methods.
     */
    private Map<String, Modifiers> methods() {
        final Map<String, ParsedClass> all = this.parseClasses();
        return all.values().stream().flatMap(parsed -> parsed.methods().stream())
            .collect(
                Collectors.toMap(ParsedMethod::name, ParsedMethod::modifiers, (a, b) -> a));
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
