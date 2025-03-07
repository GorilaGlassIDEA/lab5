package by.dima.model.service.files.io.create;

import by.dima.model.data.abstracts.model.Models;
import by.dima.model.service.files.io.write.WriteableFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateFile implements Creatable {

    private final String defaultContent = "";
    private final WriteableFile writeableFile;
    private final String path;

    public CreateFile(WriteableFile writeableFile) {
        this.path = writeableFile.getPathTo();

        this.writeableFile = writeableFile;
    }

    @Override
    public void createFile() {
        Path filePath = Paths.get(path);


        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
                writeableFile.write(defaultContent);
            } catch (IOException e) {
                System.err.println("Impossible to create file!");
            }
        }

    }
}
