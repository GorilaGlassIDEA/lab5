package by.dima.model;

import by.dima.model.data.CollectionController;
import by.dima.model.data.abstracts.model.Models;
import by.dima.model.data.command.CommandManager;
import by.dima.model.data.route.model.main.FillOutRouteModelUsingScanner;
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

import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        final String FILE_PATH = System.getenv("DATA_FILE");
        ReadableFile readableFile = new ReadFileBufferReader(FILE_PATH);
        WriteableFile writeableFile = new WriteFileOutputStreamWriter(FILE_PATH);
        Creatable creatable = new CreateFile(writeableFile);
        creatable.fileCreator();
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JavaTimeModule());
        ParserFromJson<Models> parserFromJson = new ParserFromJsonJacksonImpl(mapper);
        ParserToJson parserToJson = new ParserToJsonJacksonImpl(mapper);

        String jsonContent = readableFile.getContent();
        Models models = parserFromJson.getModels(jsonContent);


        CollectionController collectionController = new CollectionController(models, writeableFile, parserToJson);
        IdGenerateble idGenerateble = new IdGenerateMy(collectionController);
        FillOutRouteModelUsingScanner routeCreator = new FillOutRouteModelUsingScanner();

        ScannerWrapper scannerWrapper = new ScannerWrapper();
        CommandManager manager = new CommandManager(collectionController, scannerWrapper, routeCreator, writeableFile, parserToJson, idGenerateble);

        try {
            while (true) {
                manager.executeCommand();
            }
        } catch (NoSuchElementException e) {
            System.err.println("Program stopped!");
        }


    }
}