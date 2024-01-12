import hexlet.code.Formatter;
import hexlet.code.model.PrefixedPairData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.TreeSet;

public class FormatterTest {
    private static Set<PrefixedPairData> dataSet = new TreeSet<>();
    private static String expectedStylish;

    @BeforeAll
    public static void setInitialData() {
        PrefixedPairData pairData1 = new PrefixedPairData("key", true, " ");
        PrefixedPairData pairData2 = new PrefixedPairData("default", null, "-");
        PrefixedPairData pairData3 = new PrefixedPairData("default", "[1, 2, 3]", "+");
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
        String actual = Formatter.makeOutputString(dataSet, "stylish");
        Assertions.assertEquals(expectedStylish, actual);
    }
}
