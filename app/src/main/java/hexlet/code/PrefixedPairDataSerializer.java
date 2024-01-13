package hexlet.code;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import hexlet.code.model.PrefixedPairData;

import java.io.IOException;

public final class PrefixedPairDataSerializer extends StdSerializer<PrefixedPairData> {

    public PrefixedPairDataSerializer() {
        this(null);
    }

    public PrefixedPairDataSerializer(Class<PrefixedPairData> t) {
        super(t);
    }

    @Override
    public void serialize(PrefixedPairData prefixedPairData,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider)
            throws IOException {
        String prefixKey = prefixedPairData.getPrefix().concat(prefixedPairData.getKey());
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField(prefixKey, prefixedPairData.getValue());
        jsonGenerator.writeEndObject();
    }
}
