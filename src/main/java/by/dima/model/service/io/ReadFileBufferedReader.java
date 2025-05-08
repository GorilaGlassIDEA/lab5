package by.dima.model.service.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class ReadFileBufferedReader implements ReadableFile {
    @Override
    public String getContent(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        return reader.lines().collect(Collectors.joining("\n"));
    }
}
