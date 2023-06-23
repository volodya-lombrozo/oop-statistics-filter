package filter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LocalCSV implements CSV {

    private final Path csv;

    LocalCSV(final String path) {
        this(Paths.get(path));
    }

    LocalCSV(final Path path) {
        this.csv = path;
    }

    @Override
    public Reader reader() {
        try {
            return new FileReader(this.csv.toFile());
        } catch (final FileNotFoundException ex) {
            throw new IllegalStateException(
                String.format("Problem with reading CSV file %s", this.csv),
                ex
            );
        }
    }
}
