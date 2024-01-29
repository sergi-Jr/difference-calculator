package hexlet.code.formatters;

import hexlet.code.StructureObjectStatus;

import java.util.List;
import java.util.Map;

public final class StylishFormatter {

    public static String makeOutputString(List<Map<String, Object>> data) {
        StringBuilder builder = new StringBuilder("{");

        data.forEach(en -> {
            StructureObjectStatus status = (StructureObjectStatus) en.get("status");
            builder.append(System.lineSeparator() + "  ");
            builder.append(status.getPrefix());
            builder.append(" ");
            if (status == StructureObjectStatus.REPLACE) {
                builder.append(uniteKeyValue(en.get("key"), en.get("value")));
                builder.append(System.lineSeparator());
                builder.append("  + ");
                builder.append(uniteKeyValue(en.get("key"), en.get("replacement")));
            } else {
                builder.append(uniteKeyValue(en.get("key"), en.get("value")));
            }
        });

        builder.append(System.lineSeparator() + "}");
        return builder.toString();
    }

    private static String uniteKeyValue(Object key, Object value) {
        if (key == null) {
            key = "null";
        } else if (value == null) {
            value = "null";
        }
        return key.toString().concat(": ").concat(value.toString());
    }
}
