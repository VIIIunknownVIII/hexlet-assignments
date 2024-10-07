
package exercise;

import java.util.Map;
import java.util.HashMap;

public class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        Map<String, String> currentData = storage.toMap();
        Map<String, String> swappedData = new HashMap<>();

        for (Map.Entry<String, String> entry : currentData.entrySet()) {
            swappedData.put(entry.getValue(), entry.getKey());
        }

        for (Map.Entry<String, String> entry : swappedData.entrySet()) {
            storage.set(entry.getKey(), entry.getValue());
        }
    }
}