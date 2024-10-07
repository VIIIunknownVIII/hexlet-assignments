package exercise;

// App.java
import java.util.List;
import java.util.stream.Collectors;

public class App {
    public static List<String> buildApartmentsList(List<Home> homes, int n) {
        return homes.stream()
                .sorted()
                .limit(n)
                .map(Home::toString)
                .collect(Collectors.toList());
    }
}
