package by.dima.model;

import by.dima.model.data.CollectionController;
import by.dima.model.data.abstracts.model.Models;
import by.dima.model.data.command.model.CommandManager;
import by.dima.model.data.route.model.main.CreateRouteUsingScanner;
import by.dima.model.request.ClientRequestUDP;
import by.dima.model.request.Clientable;
import by.dima.model.service.files.io.ScannerWrapper;
import by.dima.model.service.files.io.create.Creatable;
import by.dima.model.service.files.io.create.CreateFile;
import by.dima.model.service.files.io.read.ReadFileBufferReader;
import by.dima.model.service.files.io.read.ReadableFile;
import by.dima.model.service.files.io.write.WriteFileOutputStreamWriter;
import by.dima.model.service.files.io.write.WriteableFile;
import by.dima.model.service.files.parser.string.impl.ParserFromJsonJacksonImpl;
import by.dima.model.service.files.parser.string.impl.ParserToJsonJacksonImpl;
import by.dima.model.service.files.parser.string.model.ParserFromJson;
import by.dima.model.service.files.parser.string.model.ParserToJson;
import by.dima.model.service.generate.id.IdGenerateMy;
import by.dima.model.service.generate.id.IdGenerateble;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


import java.io.IOException;
import java.util.NoSuchElementException;

public class Main {
    public static String FILE_PATH;

    public static void main(String[] args) {

        if (System.getenv("FILE_PATH") == null) {
            FILE_PATH = System.getProperty("user.dir") + '/' + "save";
        } else {
            FILE_PATH = System.getenv("FILE_PATH") + '/' + "save";
        }
        System.out.println("Путь сохранения вашего файла: " + FILE_PATH);
        WriteableFile writeableFile = new WriteFileOutputStreamWriter(FILE_PATH);
        Creatable creatable = new CreateFile(writeableFile);
        creatable.fileCreator();
        ReadableFile readableFile = new ReadFileBufferReader(FILE_PATH);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JavaTimeModule());
        ParserFromJson<Models> parserFromJson = new ParserFromJsonJacksonImpl(mapper);
        ParserToJson parserToJson = new ParserToJsonJacksonImpl(mapper);

        try {
            String jsonContent = readableFile.getContent();

            Models models = parserFromJson.getModels(jsonContent);


            CollectionController collectionController = new CollectionController(models, writeableFile, parserToJson);
            IdGenerateble idGenerateble = new IdGenerateMy(collectionController);
            CreateRouteUsingScanner routeCreator = new CreateRouteUsingScanner();

            ScannerWrapper scannerWrapper = new ScannerWrapper();
            CommandManager manager = new CommandManager(collectionController, scannerWrapper, routeCreator, parserToJson, idGenerateble);


            Clientable clientable = new ClientRequestUDP();
            byte[] randomBytes = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

            try {
                while (true) {
                    manager.executeCommand();
                    clientable.makePost(randomBytes);
                }
            } catch (NoSuchElementException e) {
                System.err.println("Program stopped!");
            }

        } catch (IOException e) {
            System.err.println("Не удалось получить путь для сохранения объектов!");
        }
    }
}