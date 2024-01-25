package hexlet.code;

import hexlet.code.abstracts.factories.FormatterFactory;
import hexlet.code.abstracts.factories.ParserFactory;
import hexlet.code.abstracts.interfaces.IParse;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String originalFilePathString, String comparableFilePathString)
            throws InvalidPathException, IOException {
        return generate(originalFilePathString, comparableFilePathString, "stylish");
    }
    public static String generate(String originalFilePathString, String comparableFilePathString, String format)
            throws InvalidPathException, IOException {
        String originalData = new FileDataExtractor().prepare(originalFilePathString);
        String comparableData = new FileDataExtractor().prepare(comparableFilePathString);
        IParse<Map<String, Object>> originalSourceParser =
                ParserFactory.build(FilenameUtils.getExtension(originalFilePathString));
        IParse<Map<String, Object>> comparableSourceParser =
                ParserFactory.build(FilenameUtils.getExtension(comparableFilePathString));
        Map<String, Object> originalMappedData = originalSourceParser.parse(originalData);
        Map<String, Object> comparableMappedData = comparableSourceParser.parse(comparableData);
        List<Map<String, Object>> dataDifference = getDifference(originalMappedData, comparableMappedData);
        return FormatterFactory.build(format).makeOutputString(dataDifference);
    }

    public static List<Map<String, Object>> getDifference(Map<String, Object> originalMap,
                                                          Map<String, Object> mapToCompare) {
        List<Map<String, Object>> diffEntities = new LinkedList<>();
        Set<String> unitedKeys = new TreeSet<>(originalMap.keySet());
        unitedKeys.addAll(mapToCompare.keySet());

        for (String k : unitedKeys) {
            Map<String, Object> diffEntity = new LinkedHashMap<>();
            Object originalMapValue = originalMap.get(k);
            Object comparableMapValue = mapToCompare.get(k);
            if (originalMap.containsKey(k) && mapToCompare.containsKey(k)) {
                boolean compareValuesResult;
                try {
                    compareValuesResult = originalMapValue.equals(comparableMapValue);
                } catch (NullPointerException e) {
                    compareValuesResult = originalMapValue == comparableMapValue;
                }
                if (compareValuesResult) {
                    diffEntity.put("status", StructureObjectStatus.UNCHANGED);
                    diffEntity.put("key", k);
                    diffEntity.put("value", originalMapValue);
                } else {
                    diffEntity.put("status", StructureObjectStatus.REPLACE);
                    diffEntity.put("key", k);
                    diffEntity.put("value", originalMapValue);
                    diffEntity.put("replacement", comparableMapValue);
                }
                diffEntities.add(diffEntity);
            } else if (originalMap.containsKey(k)) {
                diffEntity = new LinkedHashMap<>();
                diffEntity.put("status", StructureObjectStatus.DELETE);
                diffEntity.put("key", k);
                diffEntity.put("value", originalMapValue);
                diffEntities.add(diffEntity);
            } else {
                diffEntity = new LinkedHashMap<>();
                diffEntity.put("status", StructureObjectStatus.ADD);
                diffEntity.put("key", k);
                diffEntity.put("value", comparableMapValue);
                diffEntities.add(diffEntity);
            }
        }
        return diffEntities;
    }
}
