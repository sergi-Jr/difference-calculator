package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class FixtureReader {
    private static String fixturePath = "src/test/resources/fixtures/";

    public static String readFixture(String fileName) throws IOException {
        return Files.readString(Paths.get(createFilePath(fileName)).toAbsolutePath().normalize());
    }

    public static String createFilePath(String fileName) {
        return fixturePath + fileName;
    }
}
