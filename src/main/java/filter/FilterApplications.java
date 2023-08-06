package filter;

import filter.app.AppGitHub;
import filter.app.AppRemoteZip;
import filter.csv.CSVRemote;
import filter.statistics.SoftMetrics;
import filter.statistics.StatisticCaseApplications;
import filter.statistics.StatisticsCollection;
import java.io.IOException;
import java.time.LocalDate;

public class FilterApplications {

    public static void main(String[] args) throws IOException {
        new Report(
            "entire.csv",
            new StatisticsCollection(
                new StatisticCaseApplications(
                    "Apache Derby 10.16.1.1",
                    new CSVRemote("derby/method-list-cpu.csv"),
                    new AppRemoteZip(
                        "https://dlcdn.apache.org//db/derby/db-derby-10.16.1.1/db-derby-10.16.1.1-src.zip"
                    ),
                    "org.apache.derby"
                ),
                //todo: add soft metrics for derby
                new SoftMetrics(0, LocalDate.of(2000, 1, 1), 0, 0)
            ),
            new StatisticsCollection(
                new StatisticCaseApplications(
                    "Apache Kafka 3.4.0",
                    new CSVRemote("kafka/method-list-cpu.csv"),
                    new AppGitHub(
                        "https://github.com/apache/kafka.git",
                        "3.4.0"
                    ),
                    "org.apache.kafka"
                ),
                //todo: add soft metrics for kafka
                new SoftMetrics(0, LocalDate.of(2011, 1, 1), 1030, 25400)
            ),
            new StatisticsCollection(
                new StatisticCaseApplications(
                    "Apache Tomcat 10.1.8",
                    new CSVRemote("tomcat/method-list-cpu.csv"),
                    new AppGitHub(
                        "https://github.com/apache/tomcat.git",
                        "10.1.8"
                    ),
                    "org.apache.tomcat"
                ),
                //todo: add soft metrics for tomcat
                new SoftMetrics(0, LocalDate.of(2011, 1, 1), 1030, 25400)
            ),
            new StatisticsCollection(
                new StatisticCaseApplications(
                    "Spring Framework 5.3.27",
                    new CSVRemote("spring-mvc/method-list-cpu.csv"),
                    new AppGitHub(
                        "https://github.com/spring-projects/spring-framework.git",
                        "v5.3.27"
                    ),
                    "org.springframework"
                ),
                //todo: add soft metrics for spring
                new SoftMetrics(0, LocalDate.of(2011, 1, 1), 1030, 25400)
            ),
            new StatisticsCollection(
                new StatisticCaseApplications(
                    "Takes 1.24.4",
                    new CSVRemote("takes/method-list-cpu.csv"),
                    new AppGitHub(
                        "https://github.com/yegor256/takes.git",
                        "1.24.4"
                    ),
                    "org.takes"
                ),
                //todo: add soft metrics for takes
                new SoftMetrics(0, LocalDate.of(2011, 1, 1), 1030, 25400)
            ),
            new StatisticsCollection(
                new StatisticCaseApplications(
                    "Struts 6.1.2",
                    new CSVRemote("struts/method-list-cpu.csv"),
                    new AppGitHub(
                        "https://github.com/apache/struts.git",
                        "STRUTS_6_1_2"
                    ),
                    "org.apache.struts2"
                ),
                //todo: add soft metrics for struts
                new SoftMetrics(0, LocalDate.of(2000, 1, 1), 0, 0)
            ),
            new StatisticsCollection(
                new StatisticCaseApplications(
                    "Micronaut 3.9.3",
                    new CSVRemote("micronaut/method-list-cpu.csv"),
                    new AppGitHub(
                        "https://github.com/micronaut-projects/micronaut-core.git",
                        "v3.9.3"
                    ),
                    "io.micronaut"
                ),
                //todo: add soft metrics for micronaut
                new SoftMetrics(0, LocalDate.of(2018, 1, 1), 0, 0)
            )
        ).make();
    }
}
