package exercise;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App {
    public static void save(Path path, Car car) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = car.serialize();
        Files.writeString(path, content, StandardOpenOption.WRITE);
    }

    public static Car extract(Path path) throws Exception {
        String content = Files.readString(path);
        return Car.unserialize(content);
    }
}
// END
