package filter.csv;

public class CSVCell {

    private final String header;
    private final Object value;

    public CSVCell(final String header, final Object value) {
        this.header = header;
        this.value = value;
    }
}
