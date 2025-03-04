package by.dima.model.service.files.io.create;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CreateFile implements Creatable {

    private String path;

    public CreateFile(String path) {
        this.path = path;
    }

    @Override
    public void createFile() throws IOException {
        Files.createFile(Paths.get(path));
    }
}
