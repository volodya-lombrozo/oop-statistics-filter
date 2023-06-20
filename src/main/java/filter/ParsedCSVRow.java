package filter;

import org.apache.commons.csv.CSVRecord;

final class ParsedCSVRow {

    private final CSVRecord record;

    ParsedCSVRow(final CSVRecord record) {
        this.record = record;
    }

    boolean isNotHeader() {
        return !"Method".equals(this.fullMethodName());
    }

    boolean withinPackage(final String pckg) {
        return this.fullMethodName().contains(pckg);
    }

    boolean isConstructor() {
        return this.fullMethodName().contains("<init>");
    }

    String shortMethodName() {
        final String full = this.fullMethodName();
        final int endIndex = full.lastIndexOf(" ");
        if (endIndex == -1) {
            return full;
        }
        return full.substring(0, endIndex).replaceAll("\\$", ".");
    }

    String fullMethodName() {
        return this.record.get("Method");
    }

    double getOwnTime() {
        final String raw = this.record.get("Own Time (ms)");
        final double res;
        if (raw.contains("<")) {
            res = 0.1;
        } else {
            res = Double.parseDouble(raw);
        }
        return res;
    }

    long getCount() {
        return Long.valueOf(this.record.get("Count"));
    }
}
