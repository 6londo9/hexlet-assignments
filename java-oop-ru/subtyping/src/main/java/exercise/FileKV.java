package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public final class FileKV implements KeyValueStorage {

    private final String filepath;
    private String storage;


    public FileKV(String filepath, Map<String, String> storage) {
        Map<String, String> deepCopy = new HashMap<>();
        for (String key : storage.keySet()) {
            deepCopy.put(key, storage.get(key));
        }

        this.storage = Utils.serialize(deepCopy);
        this.filepath = filepath;
        Utils.writeFile(this.filepath, this.storage);
    }

    @Override
    public void set(String key, String value) {
        Map<String, String> map = new HashMap<>(Utils.unserialize(storage));
        map.put(key, value);
        storage = Utils.serialize(map);
        Utils.writeFile(filepath, storage);
    }

    @Override
    public void unset(String key) {
        Map<String, String> map = new HashMap<>(Utils.unserialize(storage));
        map.remove(key);
        storage = Utils.serialize(map);
        Utils.writeFile(filepath, storage);
    }

    @Override
    public String get(String key, String defaultValue) {
        return Utils.unserialize(storage).getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = Utils.unserialize(storage);
        Map<String, String> deepCopy = new HashMap<>();
        for (String key : map.keySet()) {
            deepCopy.put(key, map.get(key));
        }

        return deepCopy;
    }
}
// END
