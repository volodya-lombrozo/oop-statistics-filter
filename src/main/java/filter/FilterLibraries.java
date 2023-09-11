package filter;

import filter.app.AppGitHub;
import filter.app.AppRemoteZip;
import filter.app.CachedApp;
import filter.csv.CSVRemote;
import filter.statistics.StatisticsCaseLibrary;
import java.io.IOException;

/**
 * Filter libraries and classify them by modifiers.
 * The new approach comparing with {@link filter.FilterLibraries}.
 */
public class FilterLibraries {

    public static void main(final String[] args) throws IOException {
        new Report(
            "libraries-new.csv",
            new StatisticsCaseLibrary(
                "Apache Derby 10.16.1.1",
                new CSVRemote("derby/method-list-cpu.csv"),
                new CachedApp(
                    new AppRemoteZip(
                        "https://dlcdn.apache.org//db/derby/db-derby-10.16.1.1/db-derby-10.16.1.1-src.zip",
                        "https://github.com/apache/derby.git"
                    )
                ),
                "org.apache.derby"
            ),
            new StatisticsCaseLibrary(
                "Apache Kafka 3.4.0",
                new CSVRemote("kafka/method-list-cpu.csv"),
                new CachedApp(
                    new AppGitHub(
                        "https://github.com/apache/kafka.git",
                        "3.4.0"
                    )
                ),
                "org.apache.kafka"
            ),
            new StatisticsCaseLibrary(
                "Apache Tomcat 10.1.8",
                new CSVRemote("tomcat/method-list-cpu.csv"),
                new CachedApp(
                    new AppGitHub(
                        "https://github.com/apache/tomcat.git",
                        "10.1.8"
                    )
                ),
                "org.apache.tomcat"
            ),
            new StatisticsCaseLibrary(
                "Apache Catalina 10.1.8",
                new CSVRemote("tomcat/method-list-cpu.csv"),
                new CachedApp(
                    new AppGitHub(
                        "https://github.com/apache/tomcat.git",
                        "10.1.8"
                    )
                ),
                "org.apache.catalina"
            ),
            new StatisticsCaseLibrary(
                "Apache Coyote 10.1.8",
                new CSVRemote("tomcat/method-list-cpu.csv"),
                new CachedApp(
                    new AppGitHub(
                        "https://github.com/apache/tomcat.git",
                        "10.1.8"
                    )
                ),
                "org.apache.coyote"
            ),
            new StatisticsCaseLibrary(
                "Spring Framework 5.3.27",
                new CSVRemote("spring-mvc/method-list-cpu.csv"),
                new CachedApp(
                    new AppGitHub(
                        "https://github.com/spring-projects/spring-framework.git",
                        "v5.3.27"
                    )
                ),
                "org.springframework"
            ),
            new StatisticsCaseLibrary(
                "Jackson Databind 2.13.5",
                new CSVRemote("spring-mvc/method-list-cpu.csv"),
                new CachedApp(
                    new AppGitHub(
                        "https://github.com/FasterXML/jackson-databind.git",
                        "jackson-databind-2.13.5"
                    )
                ),
                "com.fasterxml.jackson.databind"
            ),
            new StatisticsCaseLibrary(
                "Jackson Core 2.13.5",
                new CSVRemote("spring-mvc/method-list-cpu.csv"),
                new CachedApp(
                    new AppGitHub(
                        "https://github.com/FasterXML/jackson-core.git",
                        "jackson-core-2.13.5"
                    )
                ),
                "com.fasterxml.jackson.core"
            ),
            new StatisticsCaseLibrary(
                "Apache Tomcat 9.0.75",
                new CSVRemote("spring-mvc/method-list-cpu.csv"),
                new CachedApp(
                    new AppGitHub(
                        "https://github.com/apache/tomcat.git",
                        "9.0.75"
                    )
                ),
                "org.apache.tomcat"
            ),
            new StatisticsCaseLibrary(
                "Apache Catalina 9.0.75",
                new CSVRemote("spring-mvc/method-list-cpu.csv"),
                new CachedApp(
                    new AppGitHub(
                        "https://github.com/apache/tomcat.git",
                        "9.0.75"
                    )
                ),
                "org.apache.catalina"
            ),
            new StatisticsCaseLibrary(
                "Apache Coyote 9.0.75",
                new CSVRemote("spring-mvc/method-list-cpu.csv"),
                new CachedApp(
                    new AppGitHub(
                        "https://github.com/apache/tomcat.git",
                        "9.0.75"
                    )
                ),
                "org.apache.coyote"
            ),
            new StatisticsCaseLibrary(
                "Takes 1.24.4",
                new CSVRemote("takes/method-list-cpu.csv"),
                new CachedApp(
                    new AppGitHub(
                        "https://github.com/yegor256/takes.git",
                        "1.24.4"
                    )
                ),
                "org.takes"
            ),
            new StatisticsCaseLibrary(
                "Cactoos 0.54.0",
                new CSVRemote("takes/method-list-cpu.csv"),
                new CachedApp(
                    new AppGitHub(
                        "https://github.com/yegor256/cactoos.git",
                        "0.54.0"
                    )
                ),
                "org.cactoos"
            ),
            new StatisticsCaseLibrary(
                "Opensymphony 2.4.2",
                new CSVRemote("struts/method-list-cpu.csv"),
                new CachedApp(
                    new AppGitHub(
                        "https://github.com/apache/struts.git",
                        "STRUTS_6_1_2"
                    )
                ),
                "com.opensymphony"
            ),
            new StatisticsCaseLibrary(
                "Jetty 10.0.15",
                new CSVRemote("struts/method-list-cpu.csv"),
                new CachedApp(
                    new AppGitHub(
                        "https://github.com/eclipse/jetty.project.git",
                        "jetty-10.0.15"
                    )
                ),
                "org.eclipse.jetty"
            ),
            new StatisticsCaseLibrary(
                "Struts 6.1.2",
                new CSVRemote("struts/method-list-cpu.csv"),
                new CachedApp(
                    new AppGitHub(
                        "https://github.com/apache/struts.git",
                        "STRUTS_6_1_2"
                    )
                ),
                "org.apache.struts2"
            ),
            new StatisticsCaseLibrary(
                "OGNL 3.3.4",
                new CSVRemote("struts/method-list-cpu.csv"),
                new CachedApp(
                    new AppGitHub(
                        "https://github.com/orphan-oss/ognl.git",
                        "OGNL_3_3_4"
                    )
                ),
                "ognl"
            ),
            new StatisticsCaseLibrary(
                "Project Reactor 3.5.0",
                new CSVRemote("micronaut/method-list-cpu.csv"),
                new CachedApp(
                    new AppGitHub(
                        "https://github.com/reactor/reactor-core.git",
                        "v3.5.0"
                    )
                ),
                "reactor.core"
            ),
            new StatisticsCaseLibrary(
                "Netty 4.1.92.Final",
                new CSVRemote("micronaut/method-list-cpu.csv"),
                new CachedApp(
                    new AppGitHub(
                        "https://github.com/netty/netty.git",
                        "netty-4.1.92.Final"
                    )
                ),
                "io.netty"
            ),
            new StatisticsCaseLibrary(
                "Micronaut 3.9.3",
                new CSVRemote("micronaut/method-list-cpu.csv"),
                new CachedApp(
                    new AppGitHub(
                        "https://github.com/micronaut-projects/micronaut-core.git",
                        "v3.9.3"
                    )
                ),
                "io.micronaut"
            ),
            new StatisticsCaseLibrary(
                "Vert.X 4.4.4",
                new CSVRemote("vertx/method-list-cpu.csv"),
                new CachedApp(
                    new AppGitHub(
                        "https://github.com/eclipse-vertx/vert.x.git",
                        "4.4.4"
                    )
                ),
                "io.vertx"
            ),
            new StatisticsCaseLibrary(
                "Netty 4.1.94.Final",
                new CSVRemote("vertx/method-list-cpu.csv"),
                new CachedApp(
                    new AppGitHub(
                        "https://github.com/netty/netty.git",
                        "netty-4.1.92.Final"
                    )
                ),
                "io.netty"
            ),
            new StatisticsCaseLibrary(
                "Jackson Core 2.15.0",
                new CSVRemote("vertx/method-list-cpu.csv"),
                new CachedApp(
                    new AppGitHub(
                        "https://github.com/FasterXML/jackson-core.git",
                        "jackson-core-2.15.0"
                    )
                ),
                "com.fasterxml.jackson.core"
            ),
//            new StatisticsCaseLibrary(
//TODO: HIGH PERCENT OF NOT FOUND METHODS ~69. Some problems with that library.
//                "Dropwizard 4.0.1",
//                new CSVRemote("dropwizard/method-list-cpu.csv"),
//                new CachedApp(
//                    new AppGitHub(
//                        "https://github.com/dropwizard/dropwizard.git",
//                        "v4.0.1"
//                    )
//                ),
//                "io.dropwizard"
//            ),
            new StatisticsCaseLibrary(
                "Jetty 11.0.15",
                new CSVRemote("dropwizard/method-list-cpu.csv"),
                new CachedApp(
                    new CachedApp(
                        new AppGitHub(
                            "https://github.com/eclipse/jetty.project.git",
                            "jetty-11.0.15"
                        )
                    )
                ),
                "org.eclipse.jetty"
            ),
            new StatisticsCaseLibrary(
                "Jackson Core 2.15.2",
                new CSVRemote("dropwizard/method-list-cpu.csv"),
                new CachedApp(
                    new AppGitHub(
                        "https://github.com/FasterXML/jackson-core.git",
                        "jackson-core-2.15.2"
                    )
                ),
                "com.fasterxml.jackson.core"
            ),
            new StatisticsCaseLibrary(
                "Jackson Databind 2.15.2",
                new CSVRemote("dropwizard/method-list-cpu.csv"),
                new CachedApp(
                    new AppGitHub(
                        "https://github.com/FasterXML/jackson-databind.git",
                        "jackson-databind-2.15.2"
                    )
                ),
                "com.fasterxml.jackson.databind"
            ),
            new StatisticsCaseLibrary(
                "Logback 1.4.8",
                new CSVRemote("dropwizard/method-list-cpu.csv"),
                new CachedApp(
                    new AppGitHub(
                        "https://github.com/qos-ch/logback.git",
                        "v_1.4.8"
                    )
                ),
                "ch.qos.logback"
            ),
            new StatisticsCaseLibrary(
                "Dropwizard Metrics 4.2.19",
                new CSVRemote("dropwizard/method-list-cpu.csv"),
                new CachedApp(
                    new AppGitHub(
                        "https://github.com/dropwizard/metrics.git",
                        "v4.2.19"
                    )
                ),
                "com.codahale.metrics"
            )
//            ,
//            new StatisticsCaseLibrary(
//                "Jakarta Servlet 5.0.0",
//                new CSVRemote("dropwizard/method-list-cpu.csv"),
//                new CachedApp(
//                    new AppGitHub(
//                        "https://github.com/jakartaee/servlet.git",
//                        "5.0.0-RELEASE"
//                    )
//                ),
//                "jakarta.servlet"
//            )
        ).make();
    }

}
