package filter;

public interface Statistics {

    Statistics add(Statistics statistics);

    Object[] csvRow(String title);

    String[] headers();
}
