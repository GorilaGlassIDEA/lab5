package by.dima.model.service.files.io.read;

import by.dima.model.service.files.io.create.Creatable;
import by.dima.model.service.files.io.create.CreateFile;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFileFiles extends ReadModel {
    private String contentFile;
    @Setter
    @Getter
    private String filePath;

    public ReadFileFiles(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getContent() {
        try {
            contentFile = Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("Не прочитать файл!");
        }
        return contentFile;
    }
}
