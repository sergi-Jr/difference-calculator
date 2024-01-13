package hexlet.abstracts;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.io.IOException;

public final class FormatterFactory {
    public static IFormatter build(String format) throws IOException {
        switch (format) {
            case "plain":
                return new PlainFormatter();
            case "json":
                return new JsonFormatter();
            default:
                return new StylishFormatter();
        }
    }
}
