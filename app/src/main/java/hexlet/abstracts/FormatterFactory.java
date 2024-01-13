package hexlet.abstracts;

import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

public final class FormatterFactory {
    public static IFormatter build(String format) {
        switch (format) {
            case "plain":
                return new PlainFormatter();
            default:
                return new StylishFormatter();
        }
    }
}
