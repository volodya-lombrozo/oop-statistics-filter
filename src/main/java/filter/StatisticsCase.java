package filter;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

final class StatisticsCase {

    private final String title;
    private final Path csv;
    private final Path project;
    private final String[] filters;

    StatisticsCase(
        final String title,
        final String csv,
        final String project,
        final String... filters
    ) {
        this(title, Paths.get(csv), Paths.get(project), filters);
    }

    private StatisticsCase(
        final String title,
        final Path csv,
        final Path project,
        final String... filters
    ) {
        this.title = title;
        this.csv = csv;
        this.project = project;
        this.filters = filters;
    }

    Statistics statistics() {
        final Set<ParsedCSVRow> csvRows = this.parseCSV();
        final Map<String, Boolean> methods = this.methods();
        List<Statistics> all = new ArrayList<>(csvRows.size());
        for (final ParsedCSVRow row : csvRows) {
            final long total = row.getCount();
            final double time = row.getOwnTime();
            if (row.isConstructor()) {
                all.add(Statistics.constructor(total, time));
            } else if (methods.get(row.shortMethodName()) == null) {
                Logger.getLogger("PARSER")
                    .warning(
                        () -> String.format("Method not found: %s", row.fullMethodName())
                    );
                all.add(Statistics.notFound(total, time));
            } else if (methods.get(row.shortMethodName()).booleanValue()) {
                all.add(Statistics.staticMethod(total, time));
            } else {
                all.add(Statistics.instanceMethod(total, time));
            }
        }
        return all.stream().reduce(Statistics.empty(), Statistics::sum);
    }

    private Set<ParsedCSVRow> parseCSV() {
        try {
            final CSVParser parse = CSVFormat.RFC4180.withHeader(
                "Method",
                "Time (ms)",
                "Avg. Time (ms)",
                "Own Time (ms)",
                "Count"
            ).parse(
                new FileReader(this.csv.toFile())
            );
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
    private Map<String, Boolean> methods() {
        final Map<String, ParsedClass> all = this.parseClasses();
        return all.values().stream().flatMap(parsed -> parsed.methods().stream())
            .collect(Collectors.toMap(ParsedMethod::name, ParsedMethod::isStatic, (a, b) -> a));
    }

    private Map<String, ParsedClass> parseClasses() {
        try (final Stream<Path> files = Files.walk(this.project)) {
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

    String title() {
        return this.title;
    }
}
