package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.abstracts.interfaces.IParse;

import java.util.Map;

public final class MappedParser implements IParse<Map<String, Object>> {

    private ObjectMapper mapper;

    public MappedParser(ObjectMapper m) {
        mapper = m;
    }

    @Override
    public Map<String, Object> parse(String source) throws JsonProcessingException {
        return mapper.readValue(source, Map.class);
    }
}
