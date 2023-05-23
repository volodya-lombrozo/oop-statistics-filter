package jar;

import org.apache.commons.csv.CSVRecord;

public class ParsedCSVRow {

    private final CSVRecord record;

    public ParsedCSVRow(final CSVRecord record) {
        this.record = record;
    }

    public boolean isNotHeader() {
        return !this.fullMethodName().equals("Method");
    }

    public boolean withinPackage(final String pckg) {
        return this.fullMethodName().contains(pckg);
    }

    public boolean isConstructor() {
        return this.fullMethodName().contains("<init>");
    }

    public String shortMethodName() {
        final String full = fullMethodName();
        final int endIndex = full.lastIndexOf(" ");
        if(endIndex == -1){
            return full;
        }
        return full.substring(0, endIndex);
    }

    String fullMethodName() {
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
