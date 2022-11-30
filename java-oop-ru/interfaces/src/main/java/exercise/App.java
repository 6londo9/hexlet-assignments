package exercise;

import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildAppartmentsList(List<Home> appartments, int elements) {
        return appartments.stream()
                .sorted(Home::compareTo)
                .map(Home::toString)
                .limit(elements)
                .collect(Collectors.toList());

    }
}
// END
