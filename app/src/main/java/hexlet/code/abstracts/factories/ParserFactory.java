package hexlet.code.abstracts.factories;

import hexlet.code.MappedParser;
import hexlet.code.abstracts.interfaces.IParse;

public class ParserFactory {
    public static IParse build(String parseFormat) throws IllegalArgumentException {
        switch (parseFormat) {
            case "json", "yaml", "yml":
                return new MappedParser(MapperFactory.getMapper(parseFormat));
            default:
                throw new IllegalArgumentException("Unsupported parsing format");
        }
    }
}
