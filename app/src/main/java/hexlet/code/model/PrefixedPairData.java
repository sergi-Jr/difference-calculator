package hexlet.code.model;

import hexlet.abstracts.PairUnifier;
import hexlet.code.StructureObjectStatus;

public final class PrefixedPairData implements Comparable<PrefixedPairData> {
    private String key;
    private Object value;
    private StructureObjectStatus prefix;

    public PrefixedPairData(String argumentKey, Object argumentValue, StructureObjectStatus argumentPrefix) {
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

    public StructureObjectStatus getObjectStatus() {
        return prefix;
    }
    public String getPrefix() {
        return prefix.getPrefix();
    }

    public boolean equals(PrefixedPairData el2) {
        boolean compareValuesResult;
        try {
            compareValuesResult = value.equals(el2.getValue());
        } catch (NullPointerException e) {
            compareValuesResult = value == el2.getValue();
        }
        if (prefix.equals(el2.getObjectStatus())
            && key.equals(el2.getKey())
            && compareValuesResult) {
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
            compareResult = prefix.compareTo(o.getObjectStatus()) > 0 ? -1 : 1;
        }
        return compareResult;
    }

    @Override
    public String toString() {
        PairUnifier unifier = (k, v) -> {
            if (v == null) {
                v = "null";
            } else if (k == null) {
                k = "null";
            }
            return k.toString().concat(",").concat(v.toString());
        };
        return prefix.getPrefix().concat("").concat(unifier.getString(key, value));
    }
}
