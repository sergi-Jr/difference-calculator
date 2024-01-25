import hexlet.code.abstracts.factories.FormatterFactory;
import hexlet.code.abstracts.interfaces.IFormatter;
import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class FormatterFactoryTest {
    @Test
    public void buildTestPlainFormatterReturned() throws IOException {
        String format = "plain";
        IFormatter formatter = FormatterFactory.build(format);
        Assertions.assertEquals(formatter instanceof PlainFormatter, true);
    }

    @Test
    public void buildTestStylishFormatterReturned() throws IOException {
        String format = "stylish";

        IFormatter formatter = FormatterFactory.build(format);
        Assertions.assertEquals(formatter instanceof StylishFormatter, true);
    }

    @Test
    public void buildTestJsonFormatterReturned() throws IOException {
        String format = "json";

        IFormatter formatter = FormatterFactory.build(format);
        Assertions.assertEquals(formatter instanceof JsonFormatter, true);
    }
}
