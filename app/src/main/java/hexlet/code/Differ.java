package hexlet.code;

import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(String originalFilePathString, String comparableFilePathString)
            throws InvalidPathException, IOException {
        return generate(originalFilePathString, comparableFilePathString, "stylish");
    }
    public static String generate(String originalFilePathString, String comparableFilePathString, String format)
            throws InvalidPathException, IOException {
        String originalData = new FileDataExtractor().prepare(originalFilePathString);
        String comparableData = new FileDataExtractor().prepare(comparableFilePathString);
        String originalSourceParseFormat = FilenameUtils.getExtension(originalFilePathString);
        String comparableSourceParseFormat = FilenameUtils.getExtension(comparableFilePathString);

        MappedParser originalSourceParser = new MappedParser(originalSourceParseFormat);
        MappedParser comparableSourceParser = new MappedParser(comparableSourceParseFormat);

        Map<String, Object> originalMappedData = originalSourceParser.parse(originalData);
        Map<String, Object> comparableMappedData = comparableSourceParser.parse(comparableData);

        List<Map<String, Object>> dataDiff = DiffGenerator.getDifference(originalMappedData, comparableMappedData);
        return FormatterSwitcher.formatData(format, dataDiff);
    }
}
