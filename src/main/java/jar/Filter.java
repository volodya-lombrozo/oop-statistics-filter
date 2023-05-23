package jar;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

/**
 * Hello world!
 *
 */
public class Filter {

    final static Path csvPath = Paths.get(
        "/Users/lombrozo/Workspace/OpenSource/oop/results/apache-derby/method-list-cpu.csv"
    );

    final static Path repositoryPath = Paths.get(
        "/Users/lombrozo/Workspace/OpenSource/derby"
    );

    final static String pckg = "org.apache.derby";

    public static void main(final String[] args) {
        final Set<ParsedCSVRow> csvRows = Filter.parseCSV();
        final Map<String, Boolean> methods = Filter.methods();
        List<Statistics> all = new ArrayList<>(csvRows.size());
        for (final ParsedCSVRow row : csvRows) {
            if (row.isConstructor()) {
                all.add(Statistics.constructor());
            } else if (methods.get(row.shortMethodName()) == null) {
                System.out.println("Method not found: " + row.fullMethodName());
                all.add(Statistics.notFound());
            } else if (methods.get(row.shortMethodName()).booleanValue()) {
                all.add(Statistics.staticMethod());
            } else {
                all.add(Statistics.instanceMethod());
            }
        }
        final Statistics common = all.stream().reduce(Statistics.empty(), Statistics::sum);
        System.out.println(common);
    }

    private static Set<ParsedCSVRow> parseCSV() {
        try {
            final CSVParser parse = CSVFormat.RFC4180.withHeader(
                "Method",
                "Time (ms)",
                "Avg. Time (ms)",
                "Own Time (ms)",
                "Count"
            ).parse(
                new FileReader(Filter.csvPath.toFile())
            );
            return parse.getRecords().stream().map(ParsedCSVRow::new)
                .filter(ParsedCSVRow::isNotHeader)
                .filter(row -> row.withinPackage(Filter.pckg))
                .collect(Collectors.toSet());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static Map<String, Boolean> methods() {
        final Map<String, ParsedClass> all = Filter.parseClasses();
        return all.values().stream().flatMap(parsed -> parsed.methods().stream())
            .collect(Collectors.toMap(ParsedMethod::name, ParsedMethod::isStatic, (a, b) -> a));
    }

    private static Map<String, ParsedClass> parseClasses() {
        try (Stream<Path> files = Files.walk(Filter.repositoryPath)) {
            final Map<String, ParsedClass> classes = files
                .filter(Files::exists)
                .filter(Files::isRegularFile)
                .parallel()
                .filter(path -> path.toString().endsWith(".java"))
                .map(Filter::parse)
                .filter(parsed -> parsed.pckg().isPresent())
                .collect(
                    Collectors.toMap(
                        parsed -> parsed.name(),
                        parsed -> parsed
                    )
                );

            return classes;
        } catch (final IOException exception) {
            throw new IllegalStateException(exception);
        }
    }

    private static ParsedClass parse(final Path path) {
        try {
            return new ParsedClass(path);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


}
