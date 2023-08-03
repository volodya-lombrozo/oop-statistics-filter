package filter.statistics;

import filter.Statistics;
import filter.csv.CSVCell;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SourceCodeMetrics implements Statistics {

    private static final long TIMEOUT = 60;

    private final Path repository;

    public SourceCodeMetrics(final Path repository) {
        this.repository = repository;
    }


    @Override
    public List<CSVCell> cells() {
        this.docker();
        return this.parseResults();
    }

    private List<CSVCell> parseResults() {
        try {
            final Path java = this.repository
                .resolve("source-meter")
                .resolve("source-meter")
                .resolve("java");
            final Path results = Files.list(java)
                .findFirst()
                .orElseThrow(
                    () -> new IllegalStateException(
                        String.format("Can not find results directory in %s", java)
                    )
                );
            final Path json = java.resolve(results).resolve("source-meter-summary.json");
            return Collections.emptyList();
        } catch (final IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    private void docker() {
        try {
            Runtime runtime = Runtime.getRuntime();
            final Process docker;
            docker = runtime.exec(this.dockerCommand());
            docker.waitFor(SourceCodeMetrics.TIMEOUT, TimeUnit.SECONDS);
            final int exitValue = docker.exitValue();
            if (exitValue != 0 && exitValue != 1) {
                throw new IllegalStateException(
                    String.format(
                        "Error while running docker command, the exit value %s is not 0, command is '%s', log is:%n %s",
                        exitValue,
                        String.join(" ", this.dockerCommand()),
                        new BufferedReader(
                            new InputStreamReader(docker.getErrorStream(), StandardCharsets.UTF_8)
                        ).lines().collect(Collectors.joining("\n"))
                    )
                );
            }
        } catch (IOException | InterruptedException ex) {
            throw new IllegalStateException(ex);
        }
    }

    private String[] dockerCommand() {
        return new String[]{
            "docker",
            "run",
            "-v",
            String.format("%s:/workdir", this.repository.toAbsolutePath()),
            "lombrozo/sourcemeter-app:latest"
        };
    }
}
