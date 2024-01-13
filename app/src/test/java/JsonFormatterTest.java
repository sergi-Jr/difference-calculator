import hexlet.code.StructureObjectStatus;
import hexlet.code.formatters.JsonFormatter;
import hexlet.code.model.PrefixedPairData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class JsonFormatterTest {
    private static Set<PrefixedPairData> dataSet = new TreeSet<>();
    private static String expectedJson;

    @BeforeAll
    public static void setInitialData() {
        PrefixedPairData pairData1 =
                new PrefixedPairData("key", true, StructureObjectStatus.UNCHANGED);
        PrefixedPairData pairData2 =
                new PrefixedPairData("default", null, StructureObjectStatus.REPLACE);
        PrefixedPairData pairData3 =
                new PrefixedPairData("default", "[1, 2, 3]", StructureObjectStatus.REWRITE);
        dataSet.add(pairData1);
        dataSet.add(pairData2);
        dataSet.add(pairData3);

        expectedJson = "[{\"-default\":null},\n"
                + "{\"+default\":\"[1, 2, 3]\"},\n"
                + "{\" key\":true}]";
    }

    @Test
    public void makeOutputStringTestJson() throws IOException {
        String actual = new JsonFormatter().makeOutputString(dataSet);
        Assertions.assertEquals(expectedJson, actual);
    }
}
