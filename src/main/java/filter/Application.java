package filter;

import java.nio.file.Path;

public interface Application {
    Path path();

    String githubUrl();

    String version();
}
