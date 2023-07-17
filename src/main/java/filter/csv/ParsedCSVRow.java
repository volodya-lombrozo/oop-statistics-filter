package filter.csv;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.csv.CSVRecord;

public final class ParsedCSVRow {

    private final CSVRecord record;

    ParsedCSVRow(final CSVRecord record) {
        this.record = record;
    }

    boolean isNotHeader() {
        return !"Method".equals(this.fullMethodName());
    }

    boolean withinPackage(final String pckg) {
        return this.fullMethodName().startsWith(pckg);
    }

    public boolean isConstructor() {
        return this.fullMethodName().contains("<init>");
    }

    public String shortMethodName() {
        final String full = this.fullMethodName();
        final int endIndex = full.lastIndexOf(" ");
        if (endIndex == -1) {
            return full;
        }
        return full.substring(0, endIndex).replaceAll("\\$", ".");
    }

    /**
     * Converts
     *  ognl.OgnlRuntime.isTypeCompatible(Class, Class, int, OgnlRuntime$ArgsCompatbilityReport) OgnlRuntime.java
     * Into
     *  ognl.OgnlRuntime.isTypeCompatible(Class, Class, int, ArgsCompatbilityReport)
     * @return
     */
    public String shortMethodNameWithoutFQNForParameters() {
        final String full = this.fullMethodName();
        final int endIndex = full.lastIndexOf(" ");
        if (endIndex == -1) {
            return full;
        }
        final String withParams = full.substring(0, endIndex);
        final int startParams = withParams.indexOf("(");
        final String name = withParams.substring(0, startParams);
        final List<String> params = Arrays.stream(withParams.substring(startParams + 1,
                withParams.length() - 1
            ).split(","))
            .map(String::trim)
            .map(
                param -> {
                    if (param.contains("$")) {
                        return param.substring(param.lastIndexOf("$") + 1);
                    } else {
                        return param;
                    }
                }
            )
            .collect(Collectors.toList());
        return String.format("%s(%s)", name, String.join(", ", params));
    }


    public String fullMethodName() {
        return this.record.get("Method");
    }

    @Deprecated
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
