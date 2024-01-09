package hexlet.code.model;

import hexlet.abstracts.PairUnifier;

public class PrefixedPairData implements Comparable<PrefixedPairData> {
    private String key;
    private Object value;
    private String prefix;

    public PrefixedPairData(String argumentKey, Object argumentValue, String argumentPrefix) {
        key = argumentKey;
        value = argumentValue;
        prefix = argumentPrefix;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public String getPrefix() {
        return prefix;
    }

    public boolean equals(PrefixedPairData el2) {
        if (prefix.equals(el2.getPrefix())
            && key.equals(el2.getKey())
            && value.equals(el2.getValue())) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(PrefixedPairData o) {
        if (this.equals(o)) {
            return 0;
        }
        int compareResult = key.compareTo(o.getKey());
        if (compareResult == 0) {
            compareResult = prefix.compareTo(o.getPrefix()) > 0 ? -1 : 1;
        }
        return compareResult;
    }

    @Override
    public String toString() {
        PairUnifier unifier = (k, v) -> k.toString().concat(": ").concat(v.toString());
        return prefix.concat(" ").concat(unifier.getString(key, value));
    }
}
