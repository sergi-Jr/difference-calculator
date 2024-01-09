package hexlet.abstracts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import hexlet.abstracts.extensions.FileFormatException;

public final class MapperFactory {
    public static ObjectMapper getMapper(String fileExtension) throws FileFormatException {
        switch (fileExtension) {
            case "json":
                return new JsonMapper();
            case "yaml", "yml":
                return new YAMLMapper().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);
            default:
                throw new FileFormatException("Unsupported file extension");
        }
    }
}
