package exercise;

import java.util.*;

//        "added" — ключ отсутствовал в первом массиве, но был добавлен во второй
//        "deleted" — ключ был в первом массиве, но отсутствует во втором
//        "changed" — ключ присутствовал и в первом и во втором массиве, но значения отличаются
//        "unchanged" — ключ присутствовал и в первом и во втором массиве с одинаковыми значениями

// BEGIN
public class App {
    public static LinkedHashMap<String, String> genDiff(Map<String, Object> map1, Map<String, Object> map2) {
        LinkedHashMap<String, String> result = new LinkedHashMap<>(); // в result должны добавить значения сверху
        Set<String> treeSet = new TreeSet<>(); // должны отсортировать по алфавиту и убрать дубликаты(возможно)?
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
