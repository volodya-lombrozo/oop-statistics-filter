package filter.app;

import filter.Application;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CachedApp implements Application {

    static final Path DEFAULT_CACHE_PATH = Paths.get(System.getProperty("user.home"))
        .resolve(".cache")
        .resolve("oop-filter-cache");

    private final Application original;

    public CachedApp(final Application original) {
        this.original = original;
    }

    @Override
    public Path path() {
        try {
            if (!Files.exists(CachedApp.DEFAULT_CACHE_PATH)) {
                Files.createDirectories(CachedApp.DEFAULT_CACHE_PATH);
            }
            final String name = this.folderName();
            final Path cachedPath = CachedApp.DEFAULT_CACHE_PATH
                .resolve(name)
                .resolve(this.version());
            if (Files.exists(cachedPath) && cachedPath.toFile().listFiles().length > 0) {
                return cachedPath;
            } else {
                Files.createDirectories(cachedPath);
                final Path path = this.original.path();
                Files.move(path, cachedPath, StandardCopyOption.REPLACE_EXISTING);
                return cachedPath;
            }
        } catch (final IOException exception) {
            throw new IllegalStateException("Exception ", exception);
        }
    }

    @Override
    public String githubUrl() {
        return this.original.githubUrl();
    }

    @Override
    public String version() {
        return this.original.version();
    }

    String folderName() {
        final String[] split = this.githubUrl().split("/");
        return split[split.length - 1];
    }
}
