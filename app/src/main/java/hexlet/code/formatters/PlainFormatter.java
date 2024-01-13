package hexlet.code.formatters;

import hexlet.abstracts.IFormatter;
import hexlet.code.model.PrefixedPairData;

import java.util.ArrayList;
import java.util.Set;

public class PlainFormatter implements IFormatter {
    @Override
    public String  makeOutputString(Set<PrefixedPairData> dataSet) {
        StringBuilder builder = new StringBuilder();
        ArrayList<PrefixedPairData> dataList = new ArrayList<>(dataSet);
        for (int i = 0; i < dataList.size(); i++) {
            PrefixedPairData model = dataList.get(i);
            switch (model.getObjectStatus()) {
                case ADD:
                    builder.append("Property '");
                    builder.append(model.getKey());
                    builder.append("' was added with value: ");
                    Object outputValue = isReferenceType(model.getValue());
                    builder.append(outputValue);
                    builder.append(System.lineSeparator());
                    break;
                case REPLACE:
                    PrefixedPairData nextModel = dataList.get(i + 1);

                    builder.append("Property '");
                    builder.append(model.getKey());
                    builder.append("' was updated. From ");
                    Object outputValueFrom = isReferenceType(model.getValue());
                    builder.append(outputValueFrom);
                    builder.append(" to ");
                    Object outputValueTo = isReferenceType(nextModel.getValue());
                    builder.append(outputValueTo);
                    i++;
                    builder.append(System.lineSeparator());
                    break;
                case DELETE:
                    builder.append("Property '");
                    builder.append(model.getKey());
                    builder.append("' was removed");
                    builder.append(System.lineSeparator());
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
