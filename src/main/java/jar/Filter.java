package jar;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class Filter {

    public static void main(final String[] args) throws IOException {
        new Report(
            new StatisticsCase(
                "Apache Derby",
                "/Users/lombrozo/Workspace/OpenSource/oop/results/apache-derby/method-list-cpu.csv",
                "/Users/lombrozo/Workspace/OpenSource/derby",
                "org.apache.derby"
            ),
            new StatisticsCase(
                "Apache Kafka",
                "/Users/lombrozo/Workspace/OpenSource/oop/results/apache-kafka/method-list-cpu.csv",
                "/Users/lombrozo/Workspace/OpenSource/kafka",
                "kafka"
            ),
            new StatisticsCase(
                "Apache Tomcat",
                "/Users/lombrozo/Workspace/OpenSource/oop/results/apache-tomcat/method-list-cpu.csv",
                "/Users/lombrozo/Workspace/OpenSource/tomcat",
                "org.apache.tomcat"
            )
            ,
            new StatisticsCase(
                "Spring Framework",
                "/Users/lombrozo/Workspace/OpenSource/oop/results/spring-mvc-5.3.27/method-list-cpu.csv",
                "/Users/lombrozo/Workspace/OpenSource/spring-framework",
                "org.springframework"
            ),
            new StatisticsCase(
                "Takes Framework",
                "/Users/lombrozo/Workspace/OpenSource/oop/results/takes/method-list-cpu.csv",
                "/Users/lombrozo/Workspace/OpenSource/takes",
                "org.takes"
            )
        ).make();
    }


}
