import hexlet.abstracts.extensions.FileFormatException;
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

    @BeforeAll
    public static void setExpected() throws IOException {
        String filepathStylish = "src/test/resources/fixtures/stylishTest.txt";
        Path pathStylish = Paths.get(filepathStylish).toAbsolutePath().normalize();
        byte[] fileBytesArrStylish = Files.readAllBytes(pathStylish);

        expectedStylish = IOUtils.toString(fileBytesArrStylish, "UTF-8");
    }

    @Test
    public void callTestJson() throws FileFormatException, IOException {
        String format = "stylish";
        String actual = Differ.generate(jsonFilePath1, jsonFilePath2, format);
        Assertions.assertEquals(expectedStylish, actual);
    }

    @Test
    public void callTestYAML() throws FileFormatException, IOException {
        String format = "stylish";
        String actual = Differ.generate(ymlFilePath1, ymlFilePath2, format);
        Assertions.assertEquals(expectedStylish, actual);
    }
}
