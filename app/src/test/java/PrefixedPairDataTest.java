import hexlet.code.model.PrefixedPairData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PrefixedPairDataTest {
    private static PrefixedPairData pairData;

    @BeforeAll
    public static void CreateObjectInstance() {
        pairData = new PrefixedPairData("key", true, "-");
    }

    @Test
    public void getKeyTest() {
        Assertions.assertEquals(pairData.getKey(), "key");
    }

    @Test
    public void getValueTest() {
        Assertions.assertEquals(pairData.getValue(), true);
    }

    @Test
    public void getPrefixTest() {
        Assertions.assertEquals(pairData.getPrefix(), "-");
    }

    @Test
    public void equalsTestTrueReturned() {
        PrefixedPairData actual = new PrefixedPairData("key", true, "-");
        Assertions.assertEquals(pairData.equals(actual), true);
    }

    @Test
    public void equalsTestFalseReturned() {
        PrefixedPairData actual = new PrefixedPairData("notSameKey", 2, " ");
        Assertions.assertEquals(pairData.equals(actual), false);
    }

    @Test
    public void compareToTestZeroReturned() {
        PrefixedPairData actual = new PrefixedPairData("key", true, "-");
        Assertions.assertEquals(pairData.compareTo(actual), 0);
    }

    @Test
    public void compareToTestPositiveOneReturned() {
        PrefixedPairData actual = new PrefixedPairData("key", true, "+");
        Assertions.assertEquals(actual.compareTo(pairData), 1);
    }

    @Test
    public void compareToTestNegativeOneReturned() {
        PrefixedPairData actual = new PrefixedPairData("key", true, "+");
        Assertions.assertEquals(pairData.compareTo(actual), -1);
    }

    @Test
    public void toStringTest() {
        String correct = "- key: true";
        Assertions.assertEquals(correct, pairData.toString());
    }
}
