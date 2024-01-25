package hexlet.code.abstracts.factories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public final class MapperFactory {
    public static ObjectMapper getMapper(String dataFormat) throws IllegalArgumentException {
        switch (dataFormat) {
            case "json":
                return new JsonMapper();
            case "yaml", "yml":
                return new YAMLMapper().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);
            default:
                throw new IllegalArgumentException("Unsupported data format");
        }
    }
}
