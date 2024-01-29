package hexlet.code.formatters;

import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public final class JsonFormatter {

    public static String makeOutputString(List<Map<String, Object>> data) throws IOException {
        return new JsonMapper().writerWithDefaultPrettyPrinter().writeValueAsString(data);
    }
}
