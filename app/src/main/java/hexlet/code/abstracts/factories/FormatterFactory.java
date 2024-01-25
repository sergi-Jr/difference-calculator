package hexlet.code.abstracts.factories;

import hexlet.code.abstracts.interfaces.IFormatter;
import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.io.IOException;

public final class FormatterFactory {
    public static IFormatter build(String format) throws IOException {
        switch (format) {
            case "stylish":
                return new StylishFormatter();
            case "plain":
                return new PlainFormatter();
            case "json":
                return new JsonFormatter();
            default:
                throw new IOException("Unsupported format.");
        }
    }
}
