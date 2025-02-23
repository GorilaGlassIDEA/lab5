package by.dima.model.service.files.io.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileBufferReader extends ReadModel {
    @Override
    public String getContent(String filePath) {

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))){

        } catch (IOException e){
            System.err.println("Не удалось открыть файл!");
        }
        return "";
    }
}
