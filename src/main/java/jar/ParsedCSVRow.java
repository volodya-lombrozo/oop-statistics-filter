package jar;

import org.apache.commons.csv.CSVRecord;

public class ParsedCSVRow {

    private final CSVRecord record;

    public ParsedCSVRow(final CSVRecord record) {
        this.record = record;
    }

    public boolean isNotHeader() {
        return !this.getMethod().equals("Method");
    }

    public boolean isConstructor(){
        return this.getMethod().contains("<init>");
    }

    public String getMethod() {
        return this.record.get("Method");
    }

    public double getOwnTime() {
        final String raw = this.record.get("Own Time (ms)");
        final double res;
        if (raw.contains("<")) {
            res = 0.1;
        } else {
            res = Double.parseDouble(raw);
        }
        return res;
    }

    public long getCount() {
        return Long.valueOf(this.record.get("Count"));
    }
}
