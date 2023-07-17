package filter.csv;

import filter.CSV;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

public final class CSVRows {

    private static final String[] EMPTY = {};
    private final CSV csv;
    private final String[] filters;

    private final String[] excluded;

    public CSVRows(final CSV csv, final String[] filters) {
        this(csv, filters, CSVRows.EMPTY);
    }

    public CSVRows(final CSV csv, final String[] filters, final String[] excluded) {
        this.csv = csv;
        this.filters = filters;
        this.excluded = excluded;
    }

    public Set<ParsedCSVRow> toSet() {
        try {
            final CSVParser parse = CSVFormat.RFC4180.withHeader(
                "Method",
                "Time (ms)",
                "Avg. Time (ms)",
                "Own Time (ms)",
                "Count"
            ).parse(this.csv.reader());
            return parse.getRecords().stream().map(ParsedCSVRow::new)
                .filter(ParsedCSVRow::isNotHeader)
                .filter(this::isFiltered)
                .filter(this::isNotExcluded)
                .collect(Collectors.toSet());
        } catch (final IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    private boolean isFiltered(final ParsedCSVRow row) {
        boolean result;
        if (this.filters.length == 0) {
            result = true;
        } else {
            result = row.withinPackage(this.filters[0]);
        }
        return result;
    }

    private boolean isNotExcluded(final ParsedCSVRow row) {
        boolean result = true;
        for (final String exclusion : this.excluded) {
            if (row.fullMethodName().startsWith(exclusion)) {
                result = false;
                break;
            }
        }
        return result;
    }
}
