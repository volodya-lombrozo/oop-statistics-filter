package filter;

import java.io.IOException;

/**
 * CSV profiling filter.
 * Application that generates a report by a CSV data which was received during profiling different
 * Java applications.
 */
public class FilterApplication {

    public static void main(final String[] args) throws IOException {
        new Report(
//            new StatisticsCase(
//                "Apache Derby",
//                "/Users/lombrozo/Workspace/OpenSource/oop/src/main/profiling/derby/method-list-cpu.csv",
//                "/Users/lombrozo/Workspace/OpenSource/derby",
//                "org.apache.derby"
//            ),
//            new StatisticsCase(
//                "Apache Kafka",
//                "/Users/lombrozo/Workspace/OpenSource/oop/src/main/profiling/kafka/method-list-cpu.csv",
//                "/Users/lombrozo/Workspace/OpenSource/kafka",
//                "kafka"
//            ),
            new StatisticsCase(
                "Apache Kafka 3.4.0",
                new RemoteCSV("kafka/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/apache/kafka.git",
                    "3.4.0"
                ),
                "kafka"
            ),
            new StatisticsCase(
                "Apache Tomcat 10.1.8",
                new RemoteCSV("tomcat/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/apache/tomcat.git",
                    "10.1.8"
                ),
                "org.apache.tomcat"
            ),
            new StatisticsCase(
                "Spring Framework 5.3.27",
                new RemoteCSV("spring-mvc/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/spring-projects/spring-framework.git",
                    "v5.3.27"
                ),
                "org.springframework"
            ),
            new StatisticsCase(
                "Takes 1.24.4",
                new RemoteCSV("takes/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/yegor256/takes.git",
                    "1.24.4"
                ),
                "org.takes"
            ),
            new StatisticsCase(
                "Verification Half",
                new RemoteCSV("verification-half/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/volodya-lombrozo/cost-of-oop.git",
                    "main"
                ),
                "verification.half"
            ),
            new StatisticsCase(
                "Verification Instance",
                new RemoteCSV("verification-instance/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/volodya-lombrozo/cost-of-oop.git",
                    "main"
                ),
                "verification.instances"
            ),
            new StatisticsCase(
                "Verification Statics",
                new RemoteCSV("verification-static/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/volodya-lombrozo/cost-of-oop.git",
                    "main"
                ),
                "verification.statics"
            ),
            new StatisticsCase(
                "Struts 6.1.2",
                new RemoteCSV("struts/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/apache/struts.git",
                    "STRUTS_6_1_2"
                ),
                "org.apache.struts2"
            ),
            new StatisticsCase(
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
