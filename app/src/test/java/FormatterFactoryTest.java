import hexlet.abstracts.FormatterFactory;
import hexlet.abstracts.IFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FormatterFactoryTest {
    @Test
    public void buildTestPlainFormatterReturned() {
        String format = "plain";
        IFormatter formatter = FormatterFactory.build(format);
        Assertions.assertEquals(formatter instanceof PlainFormatter, true);
    }

    @Test
    public void buildTestStylishFormatterReturned() {
        String format = "stylish";

        IFormatter formatter = FormatterFactory.build(format);
        Assertions.assertEquals(formatter instanceof StylishFormatter, true);
    }
}
