package filter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class StatisticsCaseWithModifiers implements StatisticsCase {

    private final String title;
    private final CSV csv;
    private final Application project;
    private final String[] filters;

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
        final Set<ParsedCSVRow> csvRows = new CSVRows(this.csv, this.filters).toSet();
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

    @Override
    public String toString() {
        return String.format(
            "StatisticsCaseWithModifiers{title='%s', csv=%s, project=%s, filters=%s}",
            this.title,
            this.csv,
            this.project,
            Arrays.toString(this.filters)
        );
    }
}
