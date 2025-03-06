package by.dima.model.service.files.io.create;

import by.dima.model.data.abstracts.model.Models;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateFile implements Creatable {

    private final String path;
    private final String defaultContent = "";

    public CreateFile(String path) {
        this.path = path;
    }

    @Override
    public void createFile() {
        Path filePath = Paths.get(path);


        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
                Files.writeString(filePath, defaultContent);
            } catch (IOException e) {
                System.err.println("Impossible to create file!");
            }
        }

    }
}
