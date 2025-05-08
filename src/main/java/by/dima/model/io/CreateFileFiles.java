package by.dima.model.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CreateFileFiles implements Creatable {
    @Override
    public void fileCreator(String path) throws IOException {
        Files.createFile(Path.of(path));
    }
}
