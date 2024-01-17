import hexlet.code.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.FixtureReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    private static String filePath;
    private static Map<String, Object> map;

    @BeforeAll
    public static void init() {
        filePath = FixtureReader.createFilePath("parserTest.json");
        map = new HashMap<>();
        map.put("setting1", "Some value");
        map.put("setting2", "200");
        map.put("setting3", true);
    }

    @Test
    public void parseTest() throws IOException {
        Map<String, Object> actual = Parser.parse(filePath);
        for (String key : actual.keySet()) {
            Assertions.assertEquals(actual.get(key).equals(map.get(key)), true);
        }
    }

    @Test
    public void parseTestExceptionReturned() {
        String wrongPath = "foo.json";
        Throwable thrown = assertThrows(IOException.class, () -> {
            Map<String, Object> actual = Parser.parse(wrongPath);
        });
        Assertions.assertNotNull(thrown.getMessage());
    }
}
