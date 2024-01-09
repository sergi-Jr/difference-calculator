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

    private static String expected =
            "{\n  - follow: false\n    host: hexlet.io\n  - proxy: 123.234.53.22\n  - timeout: 50\n  + timeout: 20\n  + verbose: true\n}";
    @Test
    public void callTestJson() throws FileFormatException, IOException {
        String actual = Differ.generate(jsonFilePath1, jsonFilePath2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void callTestYAML() throws FileFormatException, IOException {
        String actual = Differ.generate(ymlFilePath1, ymlFilePath2);
        Assertions.assertEquals(expected, actual);
    }
}
