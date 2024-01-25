package hexlet.code.formatters;

import hexlet.code.StructureObjectStatus;
import hexlet.code.abstracts.interfaces.IFormatter;
import hexlet.code.abstracts.interfaces.PairUnifier;

import java.util.List;
import java.util.Map;

public final class StylishFormatter implements IFormatter {

    @Override
    public String makeOutputString(List<Map<String, Object>> data) {
        StringBuilder builder = new StringBuilder("{");
        PairUnifier unifier = (k, v) -> {
            if (v == null) {
                v = "null";
            } else if (k == null) {
                k = "null";
            }
            return k.toString().concat(": ").concat(v.toString());
        };

        data.forEach(en -> {
            StructureObjectStatus status = (StructureObjectStatus) en.get("status");
            builder.append(System.lineSeparator() + "  ");
            builder.append(status.getPrefix());
            builder.append(" ");
            if (status == StructureObjectStatus.REPLACE) {
                builder.append(unifier.getString(en.get("key"), en.get("value")));
                builder.append(System.lineSeparator());
                builder.append("  + ");
                builder.append(unifier.getString(en.get("key"), en.get("replacement")));
            } else {
                builder.append(unifier.getString(en.get("key"), en.get("value")));
            }
        });

        builder.append(System.lineSeparator() + "}");
        return builder.toString();
    }
}
