package exercise;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.io.IOException;

public class App {

    public static void save(Path path, Car car) {
        String json = car.serialize();
        try {
            Files.write(path, json.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Car extract(Path path) {
        try {
            String json = Files.readString(path);
            return Car.deserialize(json);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
