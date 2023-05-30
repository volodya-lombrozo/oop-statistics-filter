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
            new StatisticsCase(
                "Apache Derby",
                "/Users/lombrozo/Workspace/OpenSource/oop/src/main/profiling/derby/method-list-cpu.csv",
                "/Users/lombrozo/Workspace/OpenSource/derby",
                "org.apache.derby"
            ),
            new StatisticsCase(
                "Apache Kafka",
                "/Users/lombrozo/Workspace/OpenSource/oop/src/main/profiling/kafka/method-list-cpu.csv",
                "/Users/lombrozo/Workspace/OpenSource/kafka",
                "kafka"
            ),
            new StatisticsCase(
                "Apache Tomcat",
                "/Users/lombrozo/Workspace/OpenSource/oop/src/main/profiling/tomcat/method-list-cpu.csv",
                "/Users/lombrozo/Workspace/OpenSource/tomcat",
                "org.apache.tomcat"
            )
            ,
            new StatisticsCase(
                "Spring Framework 5.3.27",
                "/Users/lombrozo/Workspace/OpenSource/oop/src/main/profiling/spring-mvc/method-list-cpu.csv",
                "/Users/lombrozo/Workspace/OpenSource/spring-framework",
                "org.springframework"
            ),
            new StatisticsCase(
                "Takes Framework",
                "/Users/lombrozo/Workspace/OpenSource/oop/src/main/profiling/takes/method-list-cpu.csv",
                "/Users/lombrozo/Workspace/OpenSource/takes",
                "org.takes"
            )
        ).make();
    }


}
