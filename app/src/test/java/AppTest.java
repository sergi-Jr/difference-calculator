
import hexlet.code.Differ;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AppTest {
    private static String jsonFilePath1 = "src/test/resources/fixtures/file1.json";
    private static String jsonFilePath2 = "src/test/resources/fixtures/file2.json";
    private static String ymlFilePath1 = "src/test/resources/fixtures/file1.yml";
    private static String ymlFilePath2 = "src/test/resources/fixtures/file2.yml";

    private static String expectedStylish;
    private static String expectedPlain;
    private static String expectedJson;

    @BeforeAll
    public static void setExpected() throws IOException {
        String filepathStylish = "src/test/resources/fixtures/stylishTest.txt";
        Path pathStylish = Paths.get(filepathStylish).toAbsolutePath().normalize();
        byte[] fileBytesArrStylish = Files.readAllBytes(pathStylish);

        expectedStylish = IOUtils.toString(fileBytesArrStylish, "UTF-8");

        String filepathPlain = "src/test/resources/fixtures/plainTest.txt";
        Path pathPlain = Paths.get(filepathPlain).toAbsolutePath().normalize();
        byte[] fileBytesArrPlain = Files.readAllBytes(pathPlain);

        expectedPlain = IOUtils.toString(fileBytesArrPlain, "UTF-8");

        String filepathJson = "src/test/resources/fixtures/jsonTest.json";
        Path pathJson = Paths.get(filepathJson).toAbsolutePath().normalize();
        byte[] fileBytesArrJson = Files.readAllBytes(pathJson);

        expectedJson = IOUtils.toString(fileBytesArrJson, "UTF-8");
    }

    @Test
    public void callTestIOExceptionReturned() {
        String wrongPath = "src/foo1.bar";
        Throwable thrown = Assertions.assertThrows(IOException.class, () -> {
            String result = Differ.generate(wrongPath, jsonFilePath2, "stylish");
        });
        Assertions.assertNotNull(thrown.getMessage());
    }

    @Test
    public void callTestJsonStylish() throws IOException {
        String format = "stylish";
        String actual = Differ.generate(jsonFilePath1, jsonFilePath2, format);
        Assertions.assertEquals(expectedStylish, actual);
    }

    @Test
    public void callTestYAMLStylish() throws IOException {
        String format = "stylish";
        String actual = Differ.generate(ymlFilePath1, ymlFilePath2, format);
        Assertions.assertEquals(expectedStylish, actual);
    }

    @Test
    public void callTestJsonPlain() throws IOException {
        String format = "plain";
        String actual = Differ.generate(jsonFilePath1, jsonFilePath2, format);
        Assertions.assertEquals(expectedPlain, actual);
    }

    @Test
    public void callTestYAMLPlain() throws IOException {
        String format = "plain";
        String actual = Differ.generate(ymlFilePath1, ymlFilePath2, format);
        Assertions.assertEquals(expectedPlain, actual);
    }

    @Test
    public void callTestJsonJson() throws IOException {
        String format = "json";
        String actual = Differ.generate(jsonFilePath1, jsonFilePath2, format);
        Assertions.assertEquals(expectedJson, actual);
    }

    @Test
    public void callTestYAMLJson() throws IOException {
        String format = "json";
        String actual = Differ.generate(ymlFilePath1, ymlFilePath2, format);
        Assertions.assertEquals(expectedJson, actual);
    }
}
