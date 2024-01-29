package hexlet.code;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public final class DiffGenerator {
    public static List<Map<String, Object>> getDifference(Map<String, Object> originalMap,
                                                          Map<String, Object> mapToCompare) {
        List<Map<String, Object>> diffEntities = new LinkedList<>();
        Set<String> unitedKeys = new TreeSet<>(originalMap.keySet());
        unitedKeys.addAll(mapToCompare.keySet());

        for (String k : unitedKeys) {
            Map<String, Object> diffEntity = new LinkedHashMap<>();
            Object originalMapValue = originalMap.get(k);
            Object comparableMapValue = mapToCompare.get(k);
            if (!originalMap.containsKey(k)) {
                createDiffEntity(diffEntity, StructureObjectStatus.ADD, k, comparableMapValue);
            } else if (!mapToCompare.containsKey(k)) {
                createDiffEntity(diffEntity, StructureObjectStatus.DELETE, k, originalMapValue);
            } else {
                if (isEqualValues(originalMapValue, comparableMapValue)) {
                    createDiffEntity(diffEntity, StructureObjectStatus.UNCHANGED, k, originalMapValue);
                } else {
                    createDiffEntity(diffEntity, StructureObjectStatus.REPLACE, k, originalMapValue);
                    diffEntity.put("replacement", comparableMapValue);
                }
            }
            diffEntities.add(diffEntity);
        }
        return diffEntities;
    }

    private static void createDiffEntity(Map<String, Object> entity, StructureObjectStatus status,
                                         String key, Object value) {
        entity.put("status", status);
        entity.put("key", key);
        entity.put("value", value);
    }

    private static boolean isEqualValues(Object v1, Object v2) {
        if (v1 == null || v2 == null) {
            return v1 == v2;
        }
        return v1.equals(v2);
    }
}
