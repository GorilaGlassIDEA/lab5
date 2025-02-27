package by.dima.model;

import by.dima.model.data.abstracts.model.Models;
import by.dima.model.data.command.impl.insert.InsertCommand;
import by.dima.model.data.route.model.main.Route;
import by.dima.model.service.files.io.AddInfo;
import by.dima.model.service.files.io.AddableInfo;
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
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        final String FILE_PATH = System.getProperty("user.dir") + System.getenv("DATA_FILE");
        ReadableFile readableFile = new ReadFileFiles(FILE_PATH);
        WriteableFile writeableFile = new WriteFileFiles(FILE_PATH);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JavaTimeModule());
        ParserFromJson<Models> parserFromJson = new ParserFromJsonJacksonImpl(mapper);
        ParserToJson parserToJson = new ParserToJsonJacksonImpl(mapper);

        String jsonContent = readableFile.getContent();
        Models models = parserFromJson.getModels(jsonContent);
        AddableInfo addableInfo = new AddInfo(models, writeableFile, parserToJson);

        Map<Long, Route> routeMap = models.getRoutesMap();
        IdGenerateble idGenerateble = new IdGenerateMy(models);

        InsertCommand insertCommand = new InsertCommand(addableInfo, parserToJson, idGenerateble);
        insertCommand.execute();

    }
}