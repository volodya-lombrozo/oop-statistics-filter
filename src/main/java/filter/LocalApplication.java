package filter;

import java.nio.file.Path;
import java.nio.file.Paths;

public class LocalApplication implements Application {

    private final Path local;

    LocalApplication(final String path) {
        this(Paths.get(path));
    }

    private LocalApplication(final Path path) {
        this.local = path;
    }

    @Override
    public Path path() {
        return this.local;
    }
}
