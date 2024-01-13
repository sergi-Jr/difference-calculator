package hexlet.code;

public enum StructureObjectStatus {
    ADD("+"),
    DELETE("-"),
    REWRITE("+"),
    REPLACE("-"),
    UNCHANGED(" ");

    private String prefix;
    StructureObjectStatus(String pr) {
        prefix = pr;
    }

    public String getPrefix() {
        return prefix;
    }
}
