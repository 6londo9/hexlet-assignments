package exercise;

import java.util.Map;

// BEGIN
public class App {

    public static void swapKeyValue(KeyValueStorage storage) {
        for (Map.Entry<String, String> entry : storage.toMap().entrySet()) {
            if (entry.getValue() != null) {
                storage.set(entry.getValue(), entry.getKey());
                storage.unset(entry.getKey());
            }
        }
    }
}
// END
