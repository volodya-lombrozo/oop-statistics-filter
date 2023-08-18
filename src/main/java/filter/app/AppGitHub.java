package filter.app;

import filter.Application;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class AppGitHub implements Application {

    private static final int TIMEOUT = 30;
    private final String url;
    private final String tag;

    public AppGitHub(
        final String http,
        final String tag
    ) {
        this.url = http;
        this.tag = tag;
    }

    @Override
    public Path path() {
        try {
            final Path destination = Files.createTempDirectory("github-app-");
            destination.toFile().deleteOnExit();
            Logger.getLogger("GitHub Downloader")
                .info(
                    () -> String.format(
                        "Downloading the git repo %s with tag %s into %s",
                        this.url,
                        this.tag,
                        destination.toAbsolutePath()
                    )
                );
            Runtime runtime = Runtime.getRuntime();
            final Process process = runtime.exec(this.command(destination));
            process.waitFor(AppGitHub.TIMEOUT, TimeUnit.SECONDS);
            final int exitValue = process.exitValue();
            if (exitValue != 0) {
                throw new IllegalStateException(
                    String.format("Error while cloning repository, the exit value %s is not 0",
                        exitValue
                    )
                );
            }
            this.markDownloadedFilesForDelete(destination);
            return destination;
        } catch (final IOException ex) {
            throw new IllegalStateException(
                String.format(
                    "Some problems encountered while working with temporary directory and downloading %s",
                    this.url
                ),
                ex
            );
        } catch (final InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(
                "Timeout while cloning the repository",
                ex
            );
        }
    }

    @Override
    public String githubUrl() {
        return this.url;
    }

    @Override
    public String version() {
        return this.tag;
    }

    private void markDownloadedFilesForDelete(final Path destination) {
        destination.toFile().deleteOnExit();
        try (final Stream<Path> walk = Files.walk(destination)) {
            walk.map(Path::toFile).forEach(File::deleteOnExit);
        } catch (final IOException ex) {
            throw new IllegalStateException(
                String.format(
                    "Some problems encountered while marking files for deletion in the folder %s",
                    destination
                ),
                ex
            );
        }
    }

    private String[] command(final Path destination) {
        return new String[]{
            "git",
            "clone",
            "--depth=1",
            String.format("--branch=%s", this.tag),
            this.url,
            destination.toAbsolutePath().toString()
        };
    }
}
