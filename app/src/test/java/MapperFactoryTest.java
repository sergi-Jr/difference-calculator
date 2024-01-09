import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import hexlet.abstracts.MapperFactory;
import hexlet.abstracts.extensions.FileFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MapperFactoryTest {
    @Test
    public void getMapperTestJsonMapperReturned() throws FileFormatException {
        String jsonExtension = "json";
        ObjectMapper mapper = MapperFactory.getMapper(jsonExtension);
        Assertions.assertEquals(mapper instanceof JsonMapper, true);
    }

    @Test
    public void getMapperTestYAMLMapperReturned() throws FileFormatException {
        String ymlExtension = "yml";
        String yamlExtension = "yaml";

        ObjectMapper mapper = MapperFactory.getMapper(ymlExtension);
        Assertions.assertEquals(mapper instanceof YAMLMapper, true);

        ObjectMapper mapperOtherCase = MapperFactory.getMapper(yamlExtension);
        Assertions.assertEquals(mapperOtherCase instanceof YAMLMapper, true);
    }

    @Test
    public void getMapperExceptionReturned() throws FileFormatException {
        String wrongExt = "doc";
        Throwable thrown = assertThrows(FileFormatException.class, () -> {
            ObjectMapper mapper = MapperFactory.getMapper(wrongExt);
        });
        Assertions.assertNotNull(thrown.getMessage());
    }
}
