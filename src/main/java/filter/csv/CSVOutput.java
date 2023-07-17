package filter.csv;

import java.io.Closeable;
import java.io.FileWriter;
import java.io.IOException;
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

    public void print(Iterable<?> cells) {
        try {
            final CSVPrinter printer = this.csv.value();
            printer.printRecord(cells);
        } catch (final Exception ex) {
            throw new IllegalStateException(
                String.format(
                    "Can't write CSV values %s",
                    cells
                ),
                ex
            );
        }
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
