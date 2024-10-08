package exercise;

import java.util.Map;
import java.util.HashMap;

public class FileKV implements KeyValueStorage {
    private String filePath;

    public FileKV(String filePath, Map<String, String> initialData) {
        this.filePath = filePath;
        Utils.writeFile(filePath, Utils.serialize(initialData));
    }

    @Override
    public void set(String key, String value) {
        Map<String, String> data = Utils.deserialize(Utils.readFile(filePath));
        data.put(key, value);
        Utils.writeFile(filePath, Utils.serialize(data));
    }

    @Override
    public void unset(String key) {
        Map<String, String> data = Utils.deserialize(Utils.readFile(filePath));
        data.remove(key);
        Utils.writeFile(filePath, Utils.serialize(data));
    }

    @Override
    public String get(String key, String defaultValue) {
        Map<String, String> data = Utils.deserialize(Utils.readFile(filePath));
        return data.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(Utils.deserialize(Utils.readFile(filePath)));
    }
}
