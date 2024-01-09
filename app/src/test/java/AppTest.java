import hexlet.code.Differ;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppTest {
    @Test
    public void testFlatJson() {
        String filePath1 = "src/test/resources/fixtures/file1.json";
        String filePath2 = "src/test/resources/fixtures/file2.json";

        try {
            var diff = Differ.generate(filePath1, filePath2);
            var d = diff;
            Assertions.assertEquals(diff, d);

        } catch (Exception e) {
            e.getMessage();
        }

    }
}
