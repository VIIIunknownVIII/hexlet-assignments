package exercise;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        Map<String, String> originalData = storage.toMap();
        Set<Entry<String, String>> entries = originalData.entrySet();

        for (Entry<String, String> entry : entries) {
            String key = entry.getKey();
            String value = entry.getValue();
            storage.unset(key);
            storage.set(value, key);
        }
    }
}
