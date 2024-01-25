import hexlet.code.StructureObjectStatus;
import hexlet.code.formatters.StylishFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StylishFormatterTest {
    private static List<Map<String, Object>> data = new ArrayList<>();
    private static String expectedStylish;

    @BeforeAll
    public static void setInitialData() {
        Map<String, Object> entity1 = Map.of("status", StructureObjectStatus.REPLACE,
                "key", "default",
                "value", "null",
                "replacement", "[1, 2, 3]");
        Map<String, Object> entity2 = Map.of("status", StructureObjectStatus.UNCHANGED,
                "key", "key",
                "value", true);
        data.add(entity1);
        data.add(entity2);
        expectedStylish = "{"
                + "\n  - default: null"
                + "\n  + default: [1, 2, 3]"
                + "\n    key: true"
                + "\n}";
    }

    @Test
    public void makeOutputStringTestStylish() {
        String actual = new StylishFormatter().makeOutputString(data);
        Assertions.assertEquals(expectedStylish, actual);
    }
}
