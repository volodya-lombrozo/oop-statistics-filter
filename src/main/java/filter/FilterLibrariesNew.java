package filter;

import java.io.IOException;

/**
 * Filter libraries and classify them by modifiers.
 * The new approach comparing with {@link filter.FilterLibraries}.
 */
public class FilterLibrariesNew {

    public static void main(final String[] args) throws IOException {
        new Report(
            "libraries-new.csv",
            new StatisticsCaseWithModifiers(
                "Apache Derby 10.16.1.1",
                new RemoteCSV("derby/method-list-cpu.csv"),
                new RemoteZipApplication(
                    "https://dlcdn.apache.org//db/derby/db-derby-10.16.1.1/db-derby-10.16.1.1-src.zip"
                ),
                "org.apache.derby"
            ),
            new StatisticsCaseWithModifiers(
                "Apache Kafka 3.4.0",
                new RemoteCSV("kafka/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/apache/kafka.git",
                    "3.4.0"
                ),
                "org.apache.kafka"
            ),
            new StatisticsCaseWithModifiers(
                "Apache Tomcat 10.1.8",
                new RemoteCSV("tomcat/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/apache/tomcat.git",
                    "10.1.8"
                ),
                "org.apache.tomcat"
            ),
            new StatisticsCaseWithModifiers(
                "Apache Catalina 10.1.8",
                new RemoteCSV("tomcat/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/apache/tomcat.git",
                    "10.1.8"
                ),
                "org.apache.catalina"
            ),
            new StatisticsCaseWithModifiers(
                "Apache Coyote 10.1.8",
                new RemoteCSV("tomcat/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/apache/tomcat.git",
                    "10.1.8"
                ),
                "org.apache.coyote"
            ),
            new StatisticsCaseWithModifiers(
                "Spring Framework 5.3.27",
                new RemoteCSV("spring-mvc/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/spring-projects/spring-framework.git",
                    "v5.3.27"
                ),
                "org.springframework"
            ),
            new StatisticsCaseWithModifiers(
                "Jackson Databind 2.13.5",
                new RemoteCSV("spring-mvc/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/FasterXML/jackson-databind.git",
                    "jackson-databind-2.13.5"
                ),
                "com.fasterxml.jackson.databind"
            ),
            new StatisticsCaseWithModifiers(
                "Jackson Core 2.13.5",
                new RemoteCSV("spring-mvc/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/FasterXML/jackson-core.git",
                    "jackson-core-2.13.5"
                ),
                "com.fasterxml.jackson.core"
            ),
            new StatisticsCaseWithModifiers(
                "Apache Tomcat 9.0.75",
                new RemoteCSV("spring-mvc/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/apache/tomcat.git",
                    "9.0.75"
                ),
                "org.apache.tomcat"
            ),
            new StatisticsCaseWithModifiers(
                "Apache Catalina 9.0.75",
                new RemoteCSV("spring-mvc/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/apache/tomcat.git",
                    "9.0.75"
                ),
                "org.apache.catalina"
            ),
            new StatisticsCaseWithModifiers(
                "Apache Coyote 9.0.75",
                new RemoteCSV("spring-mvc/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/apache/tomcat.git",
                    "9.0.75"
                ),
                "org.apache.coyote"
            ),
            new StatisticsCaseWithModifiers(
                "Takes 1.24.4",
                new RemoteCSV("takes/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/yegor256/takes.git",
                    "1.24.4"
                ),
                "org.takes"
            ),
            new StatisticsCaseWithModifiers(
                "Cactoos 0.54.0",
                new RemoteCSV("takes/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/yegor256/cactoos.git",
                    "0.54.0"
                ),
                "org.cactoos"
            ),
            new StatisticsCaseWithModifiers(
                "Opensymphony 2.4.2",
                new RemoteCSV("struts/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/apache/struts.git",
                    "STRUTS_6_1_2"
                ),
                "com.opensymphony"
            ),
            new StatisticsCaseWithModifiers(
                "Jetty 10.0.15",
                new RemoteCSV("struts/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/eclipse/jetty.project.git",
                    "jetty-10.0.15"
                ),
                "org.eclipse.jetty"
            ),
            new StatisticsCaseWithModifiers(
                "Struts 6.1.2",
                new RemoteCSV("struts/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/apache/struts.git",
                    "STRUTS_6_1_2"
                ),
                "org.apache.struts2"
            ),
            new StatisticsCaseWithModifiers(
                "OGNL 3.3.4",
                new RemoteCSV("struts/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/orphan-oss/ognl.git",
                    "OGNL_3_3_4"
                ),
                "ognl"
            ),
            new StatisticsCaseWithModifiers(
                "Project Reactor 3.5.0",
                new RemoteCSV("micronaut/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/reactor/reactor-core.git",
                    "v3.5.0"
                ),
                "reactor.core"
            ),
            new StatisticsCaseWithModifiers(
                "Netty 4.1.92.Final",
                new RemoteCSV("micronaut/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/netty/netty.git",
                    "netty-4.1.92.Final"
                ),
                "io.netty"
            ),
            new StatisticsCaseWithModifiers(
                "Micronaut 3.9.3",
                new RemoteCSV("micronaut/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/micronaut-projects/micronaut-core.git",
                    "v3.9.3"
                ),
                "io.micronaut"
            )
        ).make();
    }

}
