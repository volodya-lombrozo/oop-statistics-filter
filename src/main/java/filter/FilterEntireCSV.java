package filter;

import filter.app.GitHubApplication;
import filter.app.RemoteZipApplication;
import filter.csv.RemoteCSV;
import filter.statistics.StatisticCaseComposite;
import java.io.IOException;

public class FilterEntireCSV {

    public static void main(String[] args) throws IOException {
        new Report(
            "entire.csv",
            new StatisticCaseComposite(
                "Apache Derby 10.16.1.1",
                new RemoteCSV("derby/method-list-cpu.csv"),
                new RemoteZipApplication(
                    "https://dlcdn.apache.org//db/derby/db-derby-10.16.1.1/db-derby-10.16.1.1-src.zip"
                ),
                "org.apache.derby"
            ),
            new StatisticCaseComposite(
                "Apache Kafka 3.4.0",
                new RemoteCSV("kafka/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/apache/kafka.git",
                    "3.4.0"
                ),
                "org.apache.kafka"
            ),
            new StatisticCaseComposite(
                "Apache Tomcat 10.1.8",
                new RemoteCSV("tomcat/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/apache/tomcat.git",
                    "10.1.8"
                ),
                "org.apache.tomcat"
            ),
            new StatisticCaseComposite(
                "Spring Framework 5.3.27",
                new RemoteCSV("spring-mvc/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/spring-projects/spring-framework.git",
                    "v5.3.27"
                ),
                "org.springframework"
            ),
            new StatisticCaseComposite(
                "Takes 1.24.4",
                new RemoteCSV("takes/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/yegor256/takes.git",
                    "1.24.4"
                ),
                "org.takes"
            ),
            new StatisticCaseComposite(
                "Struts 6.1.2",
                new RemoteCSV("struts/method-list-cpu.csv"),
                new GitHubApplication(
                    "https://github.com/apache/struts.git",
                    "STRUTS_6_1_2"
                ),
                "org.apache.struts2"
            ),
            new StatisticCaseComposite(
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
