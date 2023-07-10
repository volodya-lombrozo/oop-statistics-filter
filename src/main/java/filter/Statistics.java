package filter;

public interface Statistics {

    Statistics sum(Statistics statistics);

    Object[] csvRow(String title);

    String[] headers();
}
