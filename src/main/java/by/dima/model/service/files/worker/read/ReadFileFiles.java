package by.dima.model.service.files.worker.read;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFileFiles implements ReadableFile {
    String contentFile;

    @Override
    public String getContent(String filePath) {
        try {
            contentFile = Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("Не удается открыть файл!");
        }
        return contentFile;
    }
}
