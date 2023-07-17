package filter;

import filter.app.AppGitHub;
import filter.app.AppRemoteZip;
import filter.csv.CSVRemote;
import filter.statistics.StatisticCaseComposite;
import java.io.IOException;

public class FilterApplications {

    public static void main(String[] args) throws IOException {
        new ReportNew(
            "entire.csv",
            new StatisticCaseComposite(
                "Apache Derby 10.16.1.1",
                new CSVRemote("derby/method-list-cpu.csv"),
                new AppRemoteZip(
                    "https://dlcdn.apache.org//db/derby/db-derby-10.16.1.1/db-derby-10.16.1.1-src.zip"
                ),
                "org.apache.derby"
            ),
            new StatisticCaseComposite(
                "Apache Kafka 3.4.0",
                new CSVRemote("kafka/method-list-cpu.csv"),
                new AppGitHub(
                    "https://github.com/apache/kafka.git",
                    "3.4.0"
                ),
                "org.apache.kafka"
            ),
            new StatisticCaseComposite(
                "Apache Tomcat 10.1.8",
                new CSVRemote("tomcat/method-list-cpu.csv"),
                new AppGitHub(
                    "https://github.com/apache/tomcat.git",
                    "10.1.8"
                ),
                "org.apache.tomcat"
            ),
            new StatisticCaseComposite(
                "Spring Framework 5.3.27",
                new CSVRemote("spring-mvc/method-list-cpu.csv"),
                new AppGitHub(
                    "https://github.com/spring-projects/spring-framework.git",
                    "v5.3.27"
                ),
                "org.springframework"
            ),
            new StatisticCaseComposite(
                "Takes 1.24.4",
                new CSVRemote("takes/method-list-cpu.csv"),
                new AppGitHub(
                    "https://github.com/yegor256/takes.git",
                    "1.24.4"
                ),
                "org.takes"
            ),
            new StatisticCaseComposite(
                "Struts 6.1.2",
                new CSVRemote("struts/method-list-cpu.csv"),
                new AppGitHub(
                    "https://github.com/apache/struts.git",
                    "STRUTS_6_1_2"
                ),
                "org.apache.struts2"
            ),
            new StatisticCaseComposite(
                "Micronaut 3.9.3",
                new CSVRemote("micronaut/method-list-cpu.csv"),
                new AppGitHub(
                    "https://github.com/micronaut-projects/micronaut-core.git",
                    "v3.9.3"
                ),
                "io.micronaut"
            )
        ).make();
    }
}
