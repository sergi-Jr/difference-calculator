package hexlet.abstracts;

import hexlet.code.model.PrefixedPairData;

import java.util.Set;

public interface IFormatter {
    String makeOutputString(Set<PrefixedPairData> dataSet);
}
