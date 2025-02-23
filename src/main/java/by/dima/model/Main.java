package by.dima.model;

import by.dima.model.data.command.CommandManager;
import by.dima.model.service.files.io.read.ReadFileFiles;
import by.dima.model.service.files.io.read.ReadableFile;
import by.dima.model.service.files.io.write.WriteFileFiles;
import by.dima.model.service.files.io.write.WriteableFile;

public class Main {
    public static void main(String[] args) {
        final String FILE_PATH = System.getProperty("user.dir") + System.getenv("DATA_FILE");
        ReadableFile readableFile = new ReadFileFiles();
        WriteableFile writeableFile = new WriteFileFiles(FILE_PATH);

        writeableFile.write("123");

        System.out.println();

    }
}