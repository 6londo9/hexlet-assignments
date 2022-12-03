package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public final class InMemoryKV implements KeyValueStorage {
    private final Map<String, String> map;

    public InMemoryKV(Map<String, String> database) {
        Map<String, String> deepCopy = new HashMap<>();
        for (String key : database.keySet()) {
            deepCopy.put(key, database.get(key));
        }
        this.map = deepCopy;
    }

    @Override
    public void set(String key, String value) {
        map.put(key, value);
    }

    @Override
    public void unset(String key) {
        map.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> deepCopy = new HashMap<>();
        for (String key : map.keySet()) {
            deepCopy.put(key, map.get(key));
        }

        return deepCopy;
    }
}
// END
