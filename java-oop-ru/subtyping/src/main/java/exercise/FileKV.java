package exercise;

// BEGIN

import java.util.Map;

public class FileKV implements KeyValueStorage {
    private String filePath;

    public FileKV(String filePath, Map<String, String> initialData) {
        this.filePath = filePath;
        Utils.writeFile(filePath, Utils.serialize(initialData));
    }

    private Map<String, String> loadFromFile() {
        String fileContent = Utils.readFile(filePath);
        return Utils.deserialize(fileContent);
    }

    private void saveToFile(Map<String, String> data) {
        String serializedData = Utils.serialize(data);
        Utils.writeFile(filePath, serializedData);
    }

    @Override
    public void set(String key, String value) {
        Map<String, String> data = loadFromFile();
        data.put(key, value);
        saveToFile(data);
    }

    @Override
    public void unset(String key) {
        Map<String, String> data = loadFromFile();
        data.remove(key);
        saveToFile(data);
    }

    @Override
    public String get(String key, String defaultValue) {
        Map<String, String> data = loadFromFile();
        return data.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return loadFromFile();
    }
}
// END
