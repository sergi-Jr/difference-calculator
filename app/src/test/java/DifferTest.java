import hexlet.code.Differ;
import hexlet.code.StructureObjectStatus;
import hexlet.code.model.PrefixedPairData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class DifferTest {
    private static Map<String, Object> map1;
    private static Map<String, Object> map2;
    private static Set<PrefixedPairData> set;

    @BeforeAll
    public static void initData() {
        map1 = new HashMap<>();
        map1.put("key1", true);
        map1.put("default", null);
        map1.put("string", "42");

        map2 = new HashMap<>();
        map2.put("key2", false);
        map2.put("default", "42");

        set = new TreeSet<>(PrefixedPairData::compareTo);
        set.add(new PrefixedPairData("key1", true, StructureObjectStatus.DELETE));
        set.add(new PrefixedPairData("default", null, StructureObjectStatus.REPLACE));
        set.add(new PrefixedPairData("string", "42", StructureObjectStatus.DELETE));
        set.add(new PrefixedPairData("key2", false, StructureObjectStatus.ADD));
        set.add(new PrefixedPairData("default", "42", StructureObjectStatus.REWRITE));
    }

    @Test
    public void getDifferenceTest() {
        Set<PrefixedPairData> actual = Differ.getDifference(map1, map2);
        Iterator<PrefixedPairData> iteratorExpected = set.iterator();
        Iterator<PrefixedPairData> iteratorActual = actual.iterator();

        while (iteratorActual.hasNext() && iteratorExpected.hasNext()) {
            PrefixedPairData setEl = iteratorExpected.next();
            PrefixedPairData actualEl = iteratorActual.next();
            Assertions.assertEquals(setEl.equals(actualEl), true);
        }

    }
}
