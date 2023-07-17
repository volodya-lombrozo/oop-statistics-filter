package filter.csv;

import filter.CSV;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CSVRemote implements CSV {

    private static final String DEFAULT_PATH = "https://raw.githubusercontent.com/volodya-lombrozo/cost-of-oop/main/src/main/profiling/";

    private final URL url;

    public CSVRemote(final String subpath) {
        this(CSVRemote.http(subpath));
    }

    private CSVRemote(final URL url) {
        this.url = url;
    }

    private static URL http(final String subpath) {
        try {
            return new URL(String.format("%s%s", CSVRemote.DEFAULT_PATH, subpath));
        } catch (final MalformedURLException ex) {
            throw new IllegalStateException(
                String.format("Malformed URL %s", subpath),
                ex
            );
        }
    }

    @Override
    public Reader reader() {
        try {
            final InputStream stream = this.url.openStream();
            return new InputStreamReader(stream, StandardCharsets.UTF_8);
        } catch (final IOException exception) {
            throw new IllegalStateException(
                String.format("Problem with reading CSV file by url %s ", this.url),
                exception
            );
        }
    }
}
