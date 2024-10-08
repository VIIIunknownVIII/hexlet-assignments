package exercise;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App {

    public static void save(Path filePath, Car instance) throws Exception {
        String jsonRepresentation = instance.serialize();
        Files.writeString(filePath, jsonRepresentation, StandardOpenOption.WRITE);
    }

    public static Car extract(Path filePath) throws Exception {
        String jsonRepresentation = Files.readString(filePath);
        Car instance = Car.deserialize(jsonRepresentation);
        return instance;
    }
}
// END
