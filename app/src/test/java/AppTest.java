import hexlet.abstracts.extensions.FileFormatException;
import hexlet.code.Differ;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class AppTest {
    private static String jsonFilePath1 = "src/test/resources/fixtures/file1.json";
    private static String jsonFilePath2 = "src/test/resources/fixtures/file2.json";
    private static String ymlFilePath1 = "src/test/resources/fixtures/file1.yml";
    private static String ymlFilePath2 = "src/test/resources/fixtures/file2.yml";

    private static String expectedFlat = "{"
            + "\n  - follow: false"
            + "\n    host: hexlet.io"
            + "\n  - proxy: 123.234.53.22"
            + "\n  - timeout: 50"
            + "\n  + timeout: 20"
            + "\n  + verbose: true"
            + "\n}";
    private static String expectedNested = "{"
            + "\n    chars1: [a, b, c]"
            + "\n  - chars2: [d, e, f]"
            + "\n  + chars2: false"
            + "\n  - checked: false"
            + "\n  + checked: true"
            + "\n  - default: null"
            + "\n  + default: [value1, value2]"
            + "\n  - id: 45"
            + "\n  + id: null"
            + "\n  - key1: value1"
            + "\n  + key2: value2"
            + "\n    numbers1: [1, 2, 3, 4]"
            + "\n  - numbers2: [2, 3, 4, 5]"
            + "\n  + numbers2: [22, 33, 44, 55]"
            + "\n  - numbers3: [3, 4, 5]"
            + "\n  + numbers4: [4, 5, 6]"
            + "\n  + obj1: {nestedKey=value, isNested=true}"
            + "\n  - setting1: Some value"
            + "\n  + setting1: Another value"
            + "\n  - setting2: 200"
            + "\n  + setting2: 300"
            + "\n  - setting3: true"
            + "\n  + setting3: none"
            + "\n}";
    @Test
    public void callTestJson() throws FileFormatException, IOException {
        String actual = Differ.generate(jsonFilePath1, jsonFilePath2);
        Assertions.assertEquals(expectedNested, actual);
    }

    @Test
    public void callTestYAML() throws FileFormatException, IOException {
        String actual = Differ.generate(ymlFilePath1, ymlFilePath2);
        Assertions.assertEquals(expectedNested, actual);
    }
}
