package filter;

import filter.app.AppGitHub;
import filter.csv.CSVRemote;
import filter.statistics.StatisticsCaseWithModifiers;
import java.io.IOException;

/**
 * CSV profiling filter.
 * Application that generates a report by a CSV data which was received during profiling different
 * verification applications.
 * Just checks that profiling tool works as expected.
 */
public class FilterVerificationApplications {
    public static void main(final String[] args) throws IOException {
        new Report(
            "verification.csv",
            new StatisticsCaseWithModifiers(
                "Verification Half",
                new CSVRemote("verification-half/method-list-cpu.csv"),
                new AppGitHub(
                    "https://github.com/volodya-lombrozo/cost-of-oop.git",
                    "main"
                ),
                "verification.half"
            ),
            new StatisticsCaseWithModifiers(
                "Verification Instance",
                new CSVRemote("verification-instance/method-list-cpu.csv"),
                new AppGitHub(
                    "https://github.com/volodya-lombrozo/cost-of-oop.git",
                    "main"
                ),
                "verification.instances"
            ),
            new StatisticsCaseWithModifiers(
                "Verification Statics",
                new CSVRemote("verification-static/method-list-cpu.csv"),
                new AppGitHub(
                    "https://github.com/volodya-lombrozo/cost-of-oop.git",
                    "main"
                ),
                "verification.statics"
            )
        ).make();
    }
}
