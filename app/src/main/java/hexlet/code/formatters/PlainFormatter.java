package hexlet.code.formatters;

import hexlet.code.StructureObjectStatus;
import hexlet.code.abstracts.interfaces.IFormatter;

import java.util.List;
import java.util.Map;

public final class PlainFormatter implements IFormatter {
    @Override
    public String  makeOutputString(List<Map<String, Object>> data) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < data.size(); i++) {
            Map<String, Object> entity = data.get(i);
            switch ((StructureObjectStatus) entity.get("status")) {
                case ADD:
                    builder.append("Property '");
                    builder.append(entity.get("key"));
                    builder.append("' was added with value: ");
                    Object outputValue = isReferenceType(entity.get("value"));
                    builder.append(outputValue);
                    if (i + 1 != data.size()) {
                        builder.append(System.lineSeparator());
                    }
                    break;
                case REPLACE:
                    builder.append("Property '");
                    builder.append(entity.get("key"));
                    builder.append("' was updated. From ");
                    Object outputValueFrom = isReferenceType(entity.get("value"));
                    builder.append(outputValueFrom);
                    builder.append(" to ");
                    Object outputValueTo = isReferenceType(entity.get("replacement"));
                    builder.append(outputValueTo);
                    if (i + 1 != data.size()) {
                        builder.append(System.lineSeparator());
                    }
                    break;
                case DELETE:
                    builder.append("Property '");
                    builder.append(entity.get("key"));
                    builder.append("' was removed");
                    if (i + 1 != data.size()) {
                        builder.append(System.lineSeparator());
                    }
                    break;
                default:
                    break;
            }
        }
        return builder.toString();
    }

    private Object isReferenceType(Object value) {
        if (value instanceof String) {
            return "'" + value + "'";
        } else if (value instanceof Boolean || value instanceof Number || value == null) {
            return value;
        }
        return "[complex value]";
    }
}
