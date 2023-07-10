package filter;

import java.io.IOException;

public class FilterLibrariesNew {

    public static void main(final String[] args) throws IOException {
        new Report(
            "libraries-new.csv",
            new StatisticsCaseNew(
                "Apache Derby 10.16.1.1",
                new RemoteCSV("derby/method-list-cpu.csv"),
                new RemoteZipApplication(
                    "https://dlcdn.apache.org//db/derby/db-derby-10.16.1.1/db-derby-10.16.1.1-src.zip"
                ),
                "org.apache.derby"
            ),
            new StatisticsCaseNew(
                "Apache Kafka 3.4.0",
                new RemoteCSV("kafka/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/apache/kafka.git",
                    "3.4.0"
                ),
                "org.apache.kafka"
            ),
            new StatisticsCaseNew(
                "Apache Tomcat 10.1.8",
                new RemoteCSV("tomcat/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/apache/tomcat.git",
                    "10.1.8"
                ),
                "org.apache.tomcat"
            ),
            new StatisticsCaseNew(
                "Apache Catalina 10.1.8",
                new RemoteCSV("tomcat/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/apache/tomcat.git",
                    "10.1.8"
                ),
                "org.apache.catalina"
            ),
            new StatisticsCaseNew(
                "Apache Coyote 10.1.8",
                new RemoteCSV("tomcat/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/apache/tomcat.git",
                    "10.1.8"
                ),
                "org.apache.coyote"
            ),
            new StatisticsCaseNew(
                "Spring Framework 5.3.27",
                new RemoteCSV("spring-mvc/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/spring-projects/spring-framework.git",
                    "v5.3.27"
                ),
                "org.springframework"
            ),
            new StatisticsCaseNew(
                "Jackson Databind 2.13.5",
                new RemoteCSV("spring-mvc/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/FasterXML/jackson-databind.git",
                    "jackson-databind-2.13.5"
                ),
                "com.fasterxml.jackson.databind"
            ),
            new StatisticsCaseNew(
                "Jackson Core 2.13.5",
                new RemoteCSV("spring-mvc/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/FasterXML/jackson-core.git",
                    "jackson-core-2.13.5"
                ),
                "com.fasterxml.jackson.core"
            ),
            new StatisticsCaseNew(
                "Apache Tomcat 9.0.75",
                new RemoteCSV("spring-mvc/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/apache/tomcat.git",
                    "9.0.75"
                ),
                "org.apache.tomcat"
            ),
            new StatisticsCaseNew(
                "Apache Catalina 9.0.75",
                new RemoteCSV("spring-mvc/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/apache/tomcat.git",
                    "9.0.75"
                ),
                "org.apache.catalina"
            ),
            new StatisticsCaseNew(
                "Apache Coyote 9.0.75",
                new RemoteCSV("spring-mvc/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/apache/tomcat.git",
                    "9.0.75"
                ),
                "org.apache.coyote"
            ),
            new StatisticsCaseNew(
                "Takes 1.24.4",
                new RemoteCSV("takes/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/yegor256/takes.git",
                    "1.24.4"
                ),
                "org.takes"
            ),
            new StatisticsCaseNew(
                "Cactoos 0.54.0",
                new RemoteCSV("takes/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/yegor256/cactoos.git",
                    "0.54.0"
                ),
                "org.cactoos"
            ),
            new StatisticsCaseNew(
                "Opensymphony 2.4.2",
                new RemoteCSV("struts/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/apache/struts.git",
                    "STRUTS_6_1_2"
                ),
                "com.opensymphony"
            ),
            new StatisticsCaseNew(
                "Jetty 10.0.15",
                new RemoteCSV("struts/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/eclipse/jetty.project.git",
                    "jetty-10.0.15"
                ),
                "org.eclipse.jetty"
            ),
            new StatisticsCaseNew(
                "Struts 6.1.2",
                new RemoteCSV("struts/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/apache/struts.git",
                    "STRUTS_6_1_2"
                ),
                "org.apache.struts2"
            ),
            new StatisticsCaseNew(
                "OGNL 3.3.4",
                new RemoteCSV("struts/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/orphan-oss/ognl.git",
                    "OGNL_3_3_4"
                ),
                "ognl"
            ),
            new StatisticsCaseNew(
                "Project Reactor 3.5.0",
                new RemoteCSV("micronaut/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/reactor/reactor-core.git",
                    "v3.5.0"
                ),
                "reactor.core"
            ),
            new StatisticsCaseNew(
                "Netty 4.1.92.Final",
                new RemoteCSV("micronaut/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/netty/netty.git",
                    "netty-4.1.92.Final"
                ),
                "io.netty"
            ),
            new StatisticsCaseNew(
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
