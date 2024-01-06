package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.abstracts.PairUnifier;
import hexlet.code.model.PrefixModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
            throws InvalidPathException, IOException {
        Map<String, Object> originalMappedJson = getDataFromJson(originalFilePathString);
        Map<String, Object> comparableMappedJson = getDataFromJson(comparableFilePathString);
        Set<PrefixModel> dataDifference = getDifference(originalMappedJson, comparableMappedJson);
        return makeOutputString(dataDifference);
    }

    private static Set<PrefixModel> getDifference(Map<String, Object> originalMap,
                                                  Map<String, Object> mapToCompare) {
        SortedSet<PrefixModel> diffCheckResultSet = new TreeSet<>(PrefixModel::compareTo);
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
                        PrefixModel unchangedPrefixModel = new PrefixModel(key, value, unchangedPrefix);
                        diffCheckResultSet.add(unchangedPrefixModel);
                    } else {
                        PrefixModel deletionPrefixModel = new PrefixModel(key, originalValue, deletionPrefix);
                        diffCheckResultSet.add(deletionPrefixModel);
                        PrefixModel insertionPrefixModel = new PrefixModel(key, comparableValue, insertionPrefix);
                        diffCheckResultSet.add(insertionPrefixModel);
                    }
                } else {
                    PrefixModel deletionPrefixModel = new PrefixModel(key, originalValue, deletionPrefix);
                    diffCheckResultSet.add(deletionPrefixModel);
                }
            } else {
                PrefixModel insertionPrefixModel = new PrefixModel(key, value, insertionPrefix);
                diffCheckResultSet.add(insertionPrefixModel);
            }
        });
        return diffCheckResultSet;
    }

    private static String makeOutputString(Set<PrefixModel> outputList) {

        StringBuilder builder = new StringBuilder("{");

        outputList.forEach(model -> {
            builder.append("\n  ");
            builder.append(model.toString());
        });

        builder.append("\n}");
        return builder.toString();
    }

    private static Map<String, Object> getDataFromJson(String filePathString)
            throws IOException, InvalidPathException {
        Path filepath = Paths.get(filePathString).toAbsolutePath().normalize();

        if (!Files.exists(filepath)) {
            throw new FileNotFoundException("File '" + filePathString + "' does not exist.");
        }

        byte[] fileBytesArr = Files.readAllBytes(filepath);

        return mapper.readValue(fileBytesArr, Map.class);
    }
}
