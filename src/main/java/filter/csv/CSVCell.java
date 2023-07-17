package filter.csv;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class CSVCell {

    private final String header;
    private final Object value;

    public CSVCell(final String header, final Object value) {
        this.header = header;
        this.value = value;
    }

    public String header() {
        return this.header;
    }

    public Object value() {
        return this.value;
    }
}
