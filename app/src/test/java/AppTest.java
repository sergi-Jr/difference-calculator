import utils.FixtureReader;
import hexlet.code.Differ;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class AppTest {
    private static String jsonFilePath1;
    private static String jsonFilePath2;
    private static String ymlFilePath1;
    private static String ymlFilePath2;
    private static String expectedStylish;
    private static String expectedPlain;
    private static String expectedJson;

    @BeforeAll
    public static void setExpected() throws IOException {
        jsonFilePath1 = FixtureReader.createFilePath("file1.json");
        jsonFilePath2 = FixtureReader.createFilePath("file2.json");
        ymlFilePath1 = FixtureReader.createFilePath("file1.yml");
        ymlFilePath2 = FixtureReader.createFilePath("file2.yml");
        expectedStylish = FixtureReader.readFixture("stylishTest.txt");
        expectedPlain = FixtureReader.readFixture("plainTest.txt");
        expectedJson = FixtureReader.readFixture("jsonTest.json");
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
    public void callTestIOExceptionReturnedNoFormat() {
        String wrongPath = "src/foo1.bar";
        Throwable thrown = Assertions.assertThrows(IOException.class, () -> {
            String result = Differ.generate(wrongPath, jsonFilePath2);
        });
        Assertions.assertNotNull(thrown.getMessage());
    }

    @Test
    public void callTestJsonStylishNoFormat() throws IOException {
        String actual = Differ.generate(jsonFilePath1, jsonFilePath2);
        Assertions.assertEquals(expectedStylish, actual);
    }

    @Test
    public void callTestYAMLStylishNoFormat() throws IOException {
        String actual = Differ.generate(ymlFilePath1, ymlFilePath2);
        Assertions.assertEquals(expectedStylish, actual);
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
