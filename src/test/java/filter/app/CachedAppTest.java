package filter.app;

import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CachedAppTest {


    @Test
    void retrievesFolderName() {
        final String actual = new CachedApp(
            new AppGitHub(
                "https://github.com/eclipse-vertx/vert.x.git",
                "4.4.4"
            )
        ).folderName();
        final String expected = "vert.x.git";
        Assertions.assertEquals(
            expected,
            actual,
            String.format("We expect '%s', but get '%s'", expected, actual)
        );
    }

    @Test
    void retrievesCachedRepository() {
        final Path actual = new CachedApp(
            new AppGitHub(
                "https://github.com/eclipse-vertx/vert.x.git",
                "4.4.4"
            )
        ).path();
        final Path expected = CachedApp.DEFAULT_CACHE_PATH
            .resolve("vert.x.git")
            .resolve("4.4.4");
        Assertions.assertEquals(
            expected,
            actual,
            String.format(
                "We expect '%s', but get '%s'",
                expected,
                actual
            )
        );
    }
}