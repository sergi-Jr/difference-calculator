package hexlet.code;

import hexlet.code.abstracts.interfaces.IDataPrepare;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class FileDataExtractor implements IDataPrepare {
    @Override
    public String prepare(Object source) throws IOException {
        String filepath = (String) source;
        Path path = Paths.get(filepath).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new IOException("File '" + filepath + "' does not exist.");
        }

        return Files.readString(path);
    }
}
