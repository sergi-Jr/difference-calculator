package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class FileDataExtractor {
    public String prepare(String source) throws IOException {
        String filepath = source;
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        return Files.readString(path);
    }
}
