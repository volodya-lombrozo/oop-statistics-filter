package filter;

import filter.csv.CSVCell;
import java.util.List;

public interface Statistics {

    Statistics add(Statistics statistics);

    Object[] csvRow(String title);

    String[] headers();

    List<CSVCell> cells();

}
