package exercise;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class FileKVTest {

    @Test
    void testFileKV() {
        String filePath = "src/test/resources/testFileKV.json";
        KeyValueStorage storage = new FileKV(filePath, Map.of("key", "value"));

        assertThat(storage.get("key", "default")).isEqualTo("value");
        assertThat(storage.get("unknown", "default")).isEqualTo("default");

        storage.set("key2", "value2");
        assertThat(storage.get("key2", "default")).isEqualTo("value2");

        storage.unset("key");
        assertThat(storage.get("key", "default")).isEqualTo("default");

        Map<String, String> data = storage.toMap();
        assertThat(data).containsOnlyKeys("key2").containsEntry("key2", "value2");
    }
}
