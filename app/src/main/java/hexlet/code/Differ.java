package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.abstracts.extensions.FileFormatException;
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
    private static String deletionPrefix = "-";
    private static String insertionPrefix = "+";
    private static String unchangedPrefix = " ";

    public static String generate(String originalFilePathString, String comparableFilePathString)
            throws InvalidPathException, IOException, FileFormatException {
        Map<String, Object> originalMappedData = Parser.parse(originalFilePathString);
        Map<String, Object> comparableMappedData = Parser.parse(comparableFilePathString);
        Set<PrefixedPairData> dataDifference = getDifference(originalMappedData, comparableMappedData);
        return makeOutputString(dataDifference);
    }

    private static Set<PrefixedPairData> getDifference(Map<String, Object> originalMap,
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
                    if (originalValue.equals(comparableValue)) {
                        PrefixedPairData unchangedPrefixedPairData = new PrefixedPairData(key, value, unchangedPrefix);
                        diffCheckResultSet.add(unchangedPrefixedPairData);
                    } else {
                        PrefixedPairData deletionPrefixedPairData = new PrefixedPairData(key, originalValue, deletionPrefix);
                        diffCheckResultSet.add(deletionPrefixedPairData);
                        PrefixedPairData insertionPrefixedPairData = new PrefixedPairData(key, comparableValue, insertionPrefix);
                        diffCheckResultSet.add(insertionPrefixedPairData);
                    }
                } else {
                    PrefixedPairData deletionPrefixedPairData = new PrefixedPairData(key, originalValue, deletionPrefix);
                    diffCheckResultSet.add(deletionPrefixedPairData);
                }
            } else {
                PrefixedPairData insertionPrefixedPairData = new PrefixedPairData(key, value, insertionPrefix);
                diffCheckResultSet.add(insertionPrefixedPairData);
            }
        });
        return diffCheckResultSet;
    }

    private static String makeOutputString(Set<PrefixedPairData> outputList) {

        StringBuilder builder = new StringBuilder("{");

        outputList.forEach(model -> {
            builder.append("\n  ");
            builder.append(model.toString());
        });

        builder.append("\n}");
        return builder.toString();
    }
}
