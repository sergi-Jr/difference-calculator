package hexlet.code.formatters;

import hexlet.abstracts.PairUnifier;
import hexlet.abstracts.IFormatter;
import hexlet.code.model.PrefixedPairData;

import java.util.Set;

public class StylishFormatter implements IFormatter {

    @Override
    public String makeOutputString(Set<PrefixedPairData> dataSet) {
        StringBuilder builder = new StringBuilder("{");
        PairUnifier unifier = (k, v) -> {
            if (v == null) {
                v = "null";
            } else if (k == null) {
                k = "null";
            }
            return k.toString().concat(": ").concat(v.toString());
        };
        dataSet.forEach(model -> {
            builder.append(System.lineSeparator() + "  ");
            builder.append(model.getPrefix().concat(" ")
                    .concat(unifier.getString(model.getKey(), model.getValue())));
        });

        builder.append(System.lineSeparator() + "}");
        return builder.toString();
    }
}
