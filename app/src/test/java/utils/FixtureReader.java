package utils;

import hexlet.code.FileDataExtractor;

import java.io.IOException;

public final class FixtureReader {
    private static String fixturePath = "src/test/resources/fixtures/";

    public static String readFixture(String fileName) throws IOException {
        return new FileDataExtractor().prepare(createFilePath(fileName));
    }

    public static String createFilePath(String fileName) {
        return fixturePath + fileName;
    }
}
