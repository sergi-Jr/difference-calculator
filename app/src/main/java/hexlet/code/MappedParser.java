package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public final class MappedParser {
    private ObjectMapper mapper;
    public MappedParser(String format) {
        switch (format) {
            case "json":
                mapper = new JsonMapper();
                break;
            case "yaml", "yml":
                mapper = new YAMLMapper().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);
                break;
            default:
                throw new IllegalArgumentException("Unsupported parsing format: " + format);
        }
    }

    public Map<String, Object> parse(String source) throws JsonProcessingException {
        return mapper.readValue(source, Map.class);
    }
}
