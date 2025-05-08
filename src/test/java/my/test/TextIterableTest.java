package my.test;

import by.dima.model.service.util.iteration.HashMapLinesIterable;
import by.dima.model.service.util.iteration.TextIterable;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

class TextIterableTest {
    @Test
    void Test() throws IOException {
        Map<String, String> maps =  HashMapLinesIterable.getHashMap(Files.readString(Path.of("/Users/dmitrijmartynov/IdeaProjects/lab5/execute.txt")));
        System.out.println(maps);
    }
}
