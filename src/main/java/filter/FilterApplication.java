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
//            new StatisticsCase(
//                "Apache Tomcat",
//                "/Users/lombrozo/Workspace/OpenSource/oop/src/main/profiling/tomcat/method-list-cpu.csv",
//                "/Users/lombrozo/Workspace/OpenSource/tomcat",
//                "org.apache.tomcat"
//            ),
//            new StatisticsCase(
//                "Spring Framework 5.3.27",
//                "/Users/lombrozo/Workspace/OpenSource/oop/src/main/profiling/spring-mvc/method-list-cpu.csv",
//                "/Users/lombrozo/Workspace/OpenSource/spring-framework",
//                "org.springframework"
//            ),
//            new StatisticsCase(
//                "Takes Framework",
//                "/Users/lombrozo/Workspace/OpenSource/oop/src/main/profiling/takes/method-list-cpu.csv",
//                "/Users/lombrozo/Workspace/OpenSource/takes",
//                "org.takes"
//            ),
//            new StatisticsCase(
//                "Struts Framework",
//                "/Users/lombrozo/Workspace/OpenSource/oop/src/main/profiling/struts/method-list-cpu.csv",
//                "/Users/lombrozo/Workspace/OpenSource/struts",
//                "org.apache.struts2"
//            ),
//            new StatisticsCase(
//                "Verification Half",
//                "/Users/lombrozo/Workspace/OpenSource/oop/src/main/profiling/verification-half/method-list-cpu.csv",
//                "/Users/lombrozo/Workspace/OpenSource/oop/src/main/java/verification/half",
//                "verification.half"
//            ),
//            new StatisticsCase(
//                "Verification Instance",
//                "/Users/lombrozo/Workspace/OpenSource/oop/src/main/profiling/verification-instance/method-list-cpu.csv",
//                "/Users/lombrozo/Workspace/OpenSource/oop/src/main/java/verification/instances",
//                "verification.instances"
//            ),
//            new StatisticsCase(
//                "Verification Static",
//                "/Users/lombrozo/Workspace/OpenSource/oop/src/main/profiling/verification-static/method-list-cpu.csv",
//                "/Users/lombrozo/Workspace/OpenSource/oop/src/main/java/verification/statics",
//                "verification.statics"
//            )
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
