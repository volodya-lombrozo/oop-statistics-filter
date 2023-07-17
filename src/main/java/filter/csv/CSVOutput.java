package filter.csv;

import java.io.Closeable;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.cactoos.scalar.Sticky;

public class CSVOutput implements Closeable {

    private final Sticky<CSVPrinter> csv;

    public CSVOutput(final String filename) {
        this(CSVOutput.printer(filename));
    }

    private CSVOutput(final Sticky<CSVPrinter> filename) {
        this.csv = filename;
    }

    public void print(Object... cells) {
        try {
            final CSVPrinter printer = this.csv.value();
            printer.printRecord(cells);
        } catch (final Exception ex) {
            throw new IllegalStateException(
                String.format(
                    "Can't write CSV values %s",
                    Arrays.toString(cells)
                ),
                ex
            );
        }
    }

    private static List<String> headers(CSVCell... cells) {
        return Arrays.stream(cells)
            .sequential()
            .map(CSVCell::header)
            .collect(Collectors.toList());
    }

    private static List<Object> values(CSVCell... cells) {
        return Arrays.stream(cells)
            .sequential()
            .map(CSVCell::value)
            .collect(Collectors.toList());
    }

    private static Sticky<CSVPrinter> printer(final String filename) {
        return new Sticky<>(
            () -> new CSVPrinter(
                new FileWriter(filename),
                CSVFormat.RFC4180
            )
        );
    }

    @Override
    public void close() throws IOException {
        try {
            final CSVPrinter printer = this.csv.value();
            printer.flush();
            printer.close();
        } catch (final Exception ex) {
            throw new IOException("Can't retrieve csv printer", ex);
        }
    }
}
