package by.dima.model.service.util.iteration;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HashMapLinesIterable {
    public static List<Map<String, String>> getHashMap(String text) {
        return Arrays.stream(text.split("next"))
                .filter(s -> !s.isEmpty())
                .map(block -> Arrays.stream(block.split(";"))
                        .map(s -> s.split("="))
                        .filter(s -> s.length == 2)
                        .collect(Collectors.toMap(
                                s -> s[0].strip(),
                                s -> s[1].strip()
                        )))
                .collect(Collectors.toList());
    }
}
