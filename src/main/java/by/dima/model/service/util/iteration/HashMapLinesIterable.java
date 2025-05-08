package by.dima.model.service.util.iteration;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HashMapLinesIterable {
    public static Map<String, String> getHashMap(String text) {
        return Stream.of(text.split(";"))
                .map(s -> s.replaceAll("\n", ""))
                .map(s -> s.split("="))
                .filter(s -> s.length == 2)
                .collect(Collectors.toMap(
                        s -> s[0],
                        s -> s[1]
                ));
    }
}
