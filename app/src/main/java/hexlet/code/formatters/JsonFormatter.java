package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.abstracts.interfaces.IFormatter;
import hexlet.code.abstracts.factories.MapperFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public final class JsonFormatter implements IFormatter {
    private ObjectMapper mapper;

    public JsonFormatter() throws IllegalArgumentException {
        mapper = MapperFactory.getMapper("json");
    }

    @Override
    public String makeOutputString(List<Map<String, Object>> data) throws IOException {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
    }
}
