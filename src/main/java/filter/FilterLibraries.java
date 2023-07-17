package filter;

import filter.app.AppGitHub;
import filter.app.AppRemoteZip;
import filter.csv.CSVRemote;
import filter.statistics.StatisticsCaseOld;
import java.io.IOException;

/**
 * CSV profiling filter.
 * Application that generates a report by a CSV data which was received during profiling different
 * Java libraries.
 */
public class FilterLibraries {

    public static void main(final String[] args) throws IOException {
        new Report(
            "libraries.csv",
            new StatisticsCaseOld(
                "Apache Derby 10.16.1.1",
                new CSVRemote("derby/method-list-cpu.csv"),
                new AppRemoteZip(
                    "https://dlcdn.apache.org//db/derby/db-derby-10.16.1.1/db-derby-10.16.1.1-src.zip"
                ),
                "org.apache.derby"
            ),
            new StatisticsCaseOld(
                "Apache Kafka 3.4.0",
                new CSVRemote("kafka/method-list-cpu.csv"),
                new AppGitHub(
                    "https://github.com/apache/kafka.git",
                    "3.4.0"
                ),
                "org.apache.kafka"
            ),
            new StatisticsCaseOld(
                "Apache Tomcat 10.1.8",
                new CSVRemote("tomcat/method-list-cpu.csv"),
                new AppGitHub(
                    "https://github.com/apache/tomcat.git",
                    "10.1.8"
                ),
                "org.apache.tomcat"
            ),
            new StatisticsCaseOld(
                "Apache Catalina 10.1.8",
                new CSVRemote("tomcat/method-list-cpu.csv"),
                new AppGitHub(
                    "https://github.com/apache/tomcat.git",
                    "10.1.8"
                ),
                "org.apache.catalina"
            ),
            new StatisticsCaseOld(
                "Apache Coyote 10.1.8",
                new CSVRemote("tomcat/method-list-cpu.csv"),
                new AppGitHub(
                    "https://github.com/apache/tomcat.git",
                    "10.1.8"
                ),
                "org.apache.coyote"
            ),
            new StatisticsCaseOld(
                "Spring Framework 5.3.27",
                new CSVRemote("spring-mvc/method-list-cpu.csv"),
                new AppGitHub(
                    "https://github.com/spring-projects/spring-framework.git",
                    "v5.3.27"
                ),
                "org.springframework"
            ),
            new StatisticsCaseOld(
                "Jackson Databind 2.13.5",
                new CSVRemote("spring-mvc/method-list-cpu.csv"),
                new AppGitHub(
                    "https://github.com/FasterXML/jackson-databind.git",
                    "jackson-databind-2.13.5"
                ),
                "com.fasterxml.jackson.databind"
            ),
            new StatisticsCaseOld(
                "Jackson Core 2.13.5",
                new CSVRemote("spring-mvc/method-list-cpu.csv"),
                new AppGitHub(
                    "https://github.com/FasterXML/jackson-core.git",
                    "jackson-core-2.13.5"
                ),
                "com.fasterxml.jackson.core"
            ),
            new StatisticsCaseOld(
                "Apache Tomcat 9.0.75",
                new CSVRemote("spring-mvc/method-list-cpu.csv"),
                new AppGitHub(
                    "https://github.com/apache/tomcat.git",
                    "9.0.75"
                ),
                "org.apache.tomcat"
            ),
            new StatisticsCaseOld(
                "Apache Catalina 9.0.75",
                new CSVRemote("spring-mvc/method-list-cpu.csv"),
                new AppGitHub(
                    "https://github.com/apache/tomcat.git",
                    "9.0.75"
                ),
                "org.apache.catalina"
            ),
            new StatisticsCaseOld(
                "Apache Coyote 9.0.75",
                new CSVRemote("spring-mvc/method-list-cpu.csv"),
                new AppGitHub(
                    "https://github.com/apache/tomcat.git",
                    "9.0.75"
                ),
                "org.apache.coyote"
            ),
            new StatisticsCaseOld(
                "Takes 1.24.4",
                new CSVRemote("takes/method-list-cpu.csv"),
                new AppGitHub(
                    "https://github.com/yegor256/takes.git",
                    "1.24.4"
                ),
                "org.takes"
            ),
            new StatisticsCaseOld(
                "Cactoos 0.54.0",
                new CSVRemote("takes/method-list-cpu.csv"),
                new AppGitHub(
                    "https://github.com/yegor256/cactoos.git",
                    "0.54.0"
                ),
                "org.cactoos"
            ),
            new StatisticsCaseOld(
                "Opensymphony 2.4.2",
                new CSVRemote("struts/method-list-cpu.csv"),
                new AppGitHub(
                    "https://github.com/apache/struts.git",
                    "STRUTS_6_1_2"
                ),
                "com.opensymphony"
            ),
            new StatisticsCaseOld(
                "Jetty 10.0.15",
                new CSVRemote("struts/method-list-cpu.csv"),
                new AppGitHub(
                    "https://github.com/eclipse/jetty.project.git",
                    "jetty-10.0.15"
                ),
                "org.eclipse.jetty"
            ),
            new StatisticsCaseOld(
                "Struts 6.1.2",
                new CSVRemote("struts/method-list-cpu.csv"),
                new AppGitHub(
                    "https://github.com/apache/struts.git",
                    "STRUTS_6_1_2"
                ),
                "org.apache.struts2"
            ),
            new StatisticsCaseOld(
                "OGNL 3.3.4",
                new CSVRemote("struts/method-list-cpu.csv"),
                new AppGitHub(
                    "https://github.com/orphan-oss/ognl.git",
                    "OGNL_3_3_4"
                ),
                "ognl"
            ),
            new StatisticsCaseOld(
                "Project Reactor 3.5.0",
                new CSVRemote("micronaut/method-list-cpu.csv"),
                new AppGitHub(
                    "https://github.com/reactor/reactor-core.git",
                    "v3.5.0"
                ),
                "reactor.core"
            ),
            new StatisticsCaseOld(
                "Netty 4.1.92.Final",
                new CSVRemote("micronaut/method-list-cpu.csv"),
                new AppGitHub(
                    "https://github.com/netty/netty.git",
                    "netty-4.1.92.Final"
                ),
                "io.netty"
            ),
            new StatisticsCaseOld(
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
