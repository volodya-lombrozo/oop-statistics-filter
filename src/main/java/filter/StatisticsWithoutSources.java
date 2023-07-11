package filter;

public class StatisticsWithoutSources implements Statistics{
    @Override
    public Statistics sum(final Statistics statistics) {
        return null;
    }

    @Override
    public Object[] csvRow(final String title) {
        return new Object[0];
    }

    @Override
    public String[] headers() {
        return new String[0];
    }
}
