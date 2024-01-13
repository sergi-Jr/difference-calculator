import hexlet.code.StructureObjectStatus;
import hexlet.code.formatters.StylishFormatter;
import hexlet.code.model.PrefixedPairData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.TreeSet;

public class StylishFormatterTest {
    private static Set<PrefixedPairData> dataSet = new TreeSet<>();
    private static String expectedStylish;

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

        expectedStylish = "{"
                + "\n  - default: null"
                + "\n  + default: [1, 2, 3]"
                + "\n    key: true"
                + "\n}";
    }

    @Test
    public void makeOutputStringTestStylish() {
        String actual = new StylishFormatter().makeOutputString(dataSet);
        Assertions.assertEquals(expectedStylish, actual);
    }
}
