package filter;

import java.io.IOException;

public class FilterEntireCSV {

    public static void main(String[] args) throws IOException {
        new Report(
            "entire.csv",
            new StatisticsCaseWithoutSources(
                "Apache Derby 10.16.1.1",
                new RemoteCSV("derby/method-list-cpu.csv"),
                "org.apache.derby"
            )
        ).make();
    }
}
