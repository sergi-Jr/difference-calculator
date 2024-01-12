package hexlet.code;

import hexlet.abstracts.PairUnifier;
import hexlet.code.model.PrefixedPairData;

import java.util.Set;

public final class Formatter {
    public static String makeOutputString(Set<PrefixedPairData> dataSet, String format) {
        switch (format) {
            default:
                return getStylish(dataSet);
        }
    }

    private static String getStylish(Set<PrefixedPairData> dataSet) {
        StringBuilder builder = new StringBuilder("{");
        PairUnifier unifier = (k, v) -> {
            if (v == null) {
                v = "null";
            }
            return k.toString().concat(": ").concat(v.toString());
        };
        dataSet.forEach(model -> {
            builder.append("\n  ");
            builder.append(model.getPrefix().concat(" ")
                                            .concat(unifier.getString(model.getKey(), model.getValue())));
        });

        builder.append("\n}");
        return builder.toString();
    }
}
