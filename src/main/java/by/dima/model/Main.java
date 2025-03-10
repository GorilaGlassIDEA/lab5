package by.dima.model;

import by.dima.model.data.CollectionController;
import by.dima.model.data.abstracts.model.Models;
import by.dima.model.data.command.CommandManager;
import by.dima.model.data.command.impl.creator.RouteCreator;
import by.dima.model.service.files.io.ScannerWrapper;
import by.dima.model.service.files.io.create.Creatable;
import by.dima.model.service.files.io.create.CreateFile;
import by.dima.model.service.files.io.read.ReadFileFiles;
import by.dima.model.service.files.io.read.ReadableFile;
import by.dima.model.service.files.io.write.WriteFileFiles;
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

import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        final String FILE_PATH = System.getenv("DATA_FILE");
        ReadableFile readableFile = new ReadFileFiles(FILE_PATH);
        WriteableFile writeableFile = new WriteFileFiles(FILE_PATH);
        Creatable creatable = new CreateFile(writeableFile);
        creatable.fileCreator();
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JavaTimeModule());
        ParserFromJson<Models> parserFromJson = new ParserFromJsonJacksonImpl(mapper);
        ParserToJson parserToJson = new ParserToJsonJacksonImpl(mapper);

        String jsonContent = readableFile.getContent();
        Models models = parserFromJson.getModels(jsonContent);

        IdGenerateble idGenerateble = new IdGenerateMy(models);

        CollectionController collectionController = new CollectionController(models, writeableFile, parserToJson);

        RouteCreator routeCreator = new RouteCreator();

        ScannerWrapper scannerWrapper = new ScannerWrapper();
        CommandManager manager = new CommandManager(collectionController, scannerWrapper, routeCreator, writeableFile, parserToJson, idGenerateble);

        try {
            while (true) {
                manager.executeCommand();
            }
        }catch (NoSuchElementException e){
            System.err.println("Program stopped!");
        }



    }
}