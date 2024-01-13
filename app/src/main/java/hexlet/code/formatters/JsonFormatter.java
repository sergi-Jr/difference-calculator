package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import hexlet.abstracts.IFormatter;
import hexlet.abstracts.MapperFactory;
import hexlet.code.PrefixedPairDataSerializer;
import hexlet.code.model.PrefixedPairData;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Set;

public final class JsonFormatter implements IFormatter {
    private ObjectMapper mapper;

    public JsonFormatter() throws IOException {
        mapper = MapperFactory.getMapper("json");
        SimpleModule module = new SimpleModule("PrefixedPairDataSerializer");
        module.addSerializer(PrefixedPairData.class, new PrefixedPairDataSerializer());
        mapper.registerModule(module);
    }
    @Override
    public String makeOutputString(Set<PrefixedPairData> dataSet) throws IOException {
        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, dataSet);
        return writer.toString().replaceAll("},", "}," + System.lineSeparator());
    }
}
