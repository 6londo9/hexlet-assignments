package exercise;

import java.util.*;

// BEGIN
public class App {
    public static LinkedHashMap<String, String> genDiff(Map<String, Object> map1, Map<String, Object> map2) {
        LinkedHashMap<String, String> result = new LinkedHashMap<>();
        Set<String> treeSet = new TreeSet<>();
        for (Map.Entry<String, Object> item : map1.entrySet()) {
            treeSet.add(item.getKey());
        }
        for (Map.Entry<String, Object> item : map2.entrySet()) {
            treeSet.add(item.getKey());
        }
        for (String key : treeSet) {
            result.put(key, getValue(key, map1, map2));
        }

        return result;
    }

    public static String getValue(String key, Map<String, Object> map1, Map<String, Object> map2) {
        if (map1.containsKey(key) && map2.containsKey(key)) {
            if (map1.get(key).equals(map2.get(key))) {
                return "unchanged";
            }
            return "changed";
        } else if (!map1.containsKey(key)) {
            return "added";
        } else {
            return "deleted";
        }
    }
}
//END
