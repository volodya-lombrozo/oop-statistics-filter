package filter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

public class GitHubApplication implements Application {

    private final String url;
    private final String tag;

    GitHubApplication(
        final String http,
        final String tag
    ) {
        this.url = http;
        this.tag = tag;
    }

    @Override
    public Path path() {
        try {
            final Path destination = Files.createTempDirectory("github-app");
            destination.toFile().deleteOnExit();
            System.out.println("Absolute file:" + destination.toAbsolutePath());
            Runtime runtime = Runtime.getRuntime();
            final Process process = runtime.exec(this.command(destination));
            process.waitFor(10, TimeUnit.SECONDS);
            return destination;
        } catch (IOException ex) {
            throw new IllegalStateException(
                String.format(
                    "Some problems encountered while working with temporary directory and downloading %s",
                    this.url
                ),
                ex
            );
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(
                "",
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
