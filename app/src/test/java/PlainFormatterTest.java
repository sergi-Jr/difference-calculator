import hexlet.code.StructureObjectStatus;
import hexlet.code.formatters.PlainFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlainFormatterTest {
    private static List<Map<String, Object>> data = new ArrayList<>();
    private static String expectedPlain;

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
        expectedPlain = "Property 'default' was updated. "
                + "From 'null' to '[1, 2, 3]'\n";
    }

    @Test
    public void makeOutputStringTestPlain() {
        String actual = new PlainFormatter().makeOutputString(data);
        Assertions.assertEquals(expectedPlain, actual);
    }
}
