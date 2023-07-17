package filter.app;

import filter.Application;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AppLocal implements Application {

    private final Path local;

    public AppLocal(final String path) {
        this(Paths.get(path));
    }

    private AppLocal(final Path path) {
        this.local = path;
    }

    @Override
    public Path path() {
        return this.local;
    }
}
