package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.abstracts.MapperFactory;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public final class Parser {
    public static Map<String, Object> parse(String filepath)
            throws IllegalArgumentException, IOException {
        String fileExtension = FilenameUtils.getExtension(filepath);
        ObjectMapper mapper = MapperFactory.getMapper(fileExtension);

        Path path = Paths.get(filepath).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new IOException("File '" + filepath + "' does not exist.");
        }

        byte[] fileBytesArr = Files.readAllBytes(path);

        return mapper.readValue(fileBytesArr, Map.class);
    }
}
