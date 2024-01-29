package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public final class FormatterSwitcher {
    public static String formatData(String format, List<Map<String, Object>> data) throws IOException {
        switch (format) {
            case "stylish":
                return StylishFormatter.makeOutputString(data);
            case "plain":
                return PlainFormatter.makeOutputString(data);
            case "json":
                return JsonFormatter.makeOutputString(data);
            default:
                throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }
}
