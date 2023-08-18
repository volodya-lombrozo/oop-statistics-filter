package filter.app;

import filter.Application;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppRemoteZip implements Application {

    private static final int TIMEOUT = 30;
    private static final String DEFAULT_FILENAME = "sources.zip";

    private final String url;

    private final String githubUrl;

    private final String version;

    public AppRemoteZip(final String url) {
        this(url, "undefined");
    }

    public AppRemoteZip(final String url, final String githubUrl) {
        this(url, githubUrl, "undefined");
    }

    public AppRemoteZip(
        final String url,
        final String githubUrl,
        final String version
    ) {
        this.url = url;
        this.githubUrl = githubUrl;
        this.version = version;
    }

    @Override
    public Path path() {
        try {
            final Path destination = Files.createTempDirectory("github-app-");
            Files.setPosixFilePermissions(destination,
                PosixFilePermissions.fromString("rwxrwxrwx")
            );
            destination.toFile().deleteOnExit();
            Logger.getLogger("Archive Downloader")
                .info(
                    () -> String.format(
                        "Downloading the sources archive %s into %s",
                        this.url,
                        destination.toAbsolutePath()
                    )
                );
            Runtime runtime = Runtime.getRuntime();
            final Process download = runtime.exec(this.download(destination));
            download.waitFor(AppRemoteZip.TIMEOUT, TimeUnit.SECONDS);
            final int exitValue = download.exitValue();
            if (exitValue != 0 && exitValue != 1) {
                throw new IllegalStateException(
                    String.format(
                        "Error while coping repository, the exit value %s is not 0, command is '%s', log is:%n %s",
                        exitValue,
                        String.join(" ", this.download(destination)),
                        new BufferedReader(
                            new InputStreamReader(download.getErrorStream())
                        ).lines().collect(Collectors.joining("\n"))
                    )
                );
            }
            final Process unzip = runtime.exec(this.unzip(destination));
            if (!unzip.waitFor(AppRemoteZip.TIMEOUT, TimeUnit.SECONDS)) {
                throw new IllegalStateException(
                    String.format(
                        "Timeout during unzipping, command is '%s', log is '%s'",
                        String.join(" ", this.unzip(destination)),
                        new BufferedReader(
                            new InputStreamReader(unzip.getErrorStream())
                        ).lines().collect(Collectors.joining("\n"))
                    )
                );
            }
            final int unzipExitValue = unzip.exitValue();
            if (unzipExitValue != 0) {
                throw new IllegalStateException(
                    String.format(
                        "Error while unzipping repository, the exit value %s is not 0, command is '%s'",
                        unzipExitValue,
                        String.join(" ", this.unzip(destination))
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
        return this.githubUrl;
    }

    @Override
    public String version() {
        return this.version;
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

    private String[] download(final Path destination) {
        return new String[]{
            "wget",
            "-O",
            AppRemoteZip.path(destination),
            this.url
        };
    }

    private String[] unzip(final Path destination) {
        return new String[]{
            "unzip",
            "-q",
            AppRemoteZip.path(destination),
            "-d",
            destination.toAbsolutePath().toString()
        };
    }

    private static String path(final Path destination) {
        return destination.resolve(
            AppRemoteZip.DEFAULT_FILENAME).toAbsolutePath().toString();
    }
}
