package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.abstracts.FormatterFactory;
import hexlet.code.model.PrefixedPairData;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Differ {
    private static ObjectMapper mapper = new ObjectMapper();

    public static String generate(String originalFilePathString, String comparableFilePathString, String format)
            throws InvalidPathException, IOException {
        Map<String, Object> originalMappedData = Parser.parse(originalFilePathString);
        Map<String, Object> comparableMappedData = Parser.parse(comparableFilePathString);
        Set<PrefixedPairData> dataDifference = getDifference(originalMappedData, comparableMappedData);
        return FormatterFactory.build(format).makeOutputString(dataDifference);
    }

    public static Set<PrefixedPairData> getDifference(Map<String, Object> originalMap,
                                                      Map<String, Object> mapToCompare) {
        SortedSet<PrefixedPairData> diffCheckResultSet = new TreeSet<>(PrefixedPairData::compareTo);
        List<Map.Entry<String, Object>> unitedMaps = new ArrayList<>(originalMap.entrySet());
        unitedMaps.addAll(mapToCompare.entrySet());

        unitedMaps.forEach(entry -> {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (originalMap.containsKey(key)) {
                Object originalValue = originalMap.get(key);
                if (mapToCompare.containsKey(key)) {
                    Object comparableValue = mapToCompare.get(key);
                    boolean compareValuesResult;
                    try {
                        compareValuesResult = originalValue.equals(comparableValue);
                    } catch (NullPointerException e) {
                        compareValuesResult = originalValue == comparableValue;
                    }
                    if (compareValuesResult) {
                        PrefixedPairData unchangedPrefixedData =
                                new PrefixedPairData(key, value, StructureObjectStatus.UNCHANGED);
                        diffCheckResultSet.add(unchangedPrefixedData);
                    } else {
                        PrefixedPairData deletionPrefixedData =
                                new PrefixedPairData(key, originalValue, StructureObjectStatus.REPLACE);
                        diffCheckResultSet.add(deletionPrefixedData);
                        PrefixedPairData insertionPrefixedData =
                                new PrefixedPairData(key, comparableValue, StructureObjectStatus.REWRITE);
                        diffCheckResultSet.add(insertionPrefixedData);
                    }
                } else {
                    PrefixedPairData deletionPrefixedData =
                            new PrefixedPairData(key, originalValue, StructureObjectStatus.DELETE);
                    diffCheckResultSet.add(deletionPrefixedData);
                }
            } else {
                PrefixedPairData insertionPrefixedData = new PrefixedPairData(key, value, StructureObjectStatus.ADD);
                diffCheckResultSet.add(insertionPrefixedData);
            }
        });
        return diffCheckResultSet;
    }
}
