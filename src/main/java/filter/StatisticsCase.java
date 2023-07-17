package filter;

import filter.csv.CSVCell;
import java.util.List;

public interface StatisticsCase {

    String title();

    Statistics statistics();

    List<CSVCell> cells();

}
