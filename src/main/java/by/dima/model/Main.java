package by.dima.model;

import by.dima.model.data.abstracts.model.Models;
import by.dima.model.data.collections.CollectionsManager;
import by.dima.model.data.command.CommandManager;
import by.dima.model.data.route.model.main.Route;
import by.dima.model.data.route.model.sub.Coordinates;
import by.dima.model.data.route.model.sub.LocationFrom;
import by.dima.model.data.route.model.sub.LocationTo;
import by.dima.model.service.files.io.read.ReadFileFiles;
import by.dima.model.service.files.io.read.ReadableFile;
import by.dima.model.service.files.io.write.WriteFileFiles;
import by.dima.model.service.files.io.write.WriteableFile;
import by.dima.model.service.files.parser.string.impl.ParserFromJsonJacksonImpl;
import by.dima.model.service.files.parser.string.impl.ParserToJsonJacksonImpl;
import by.dima.model.service.files.parser.string.model.ParserFromJson;
import by.dima.model.service.files.parser.string.model.ParserToJson;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        final String FILE_PATH = System.getProperty("user.dir") + System.getenv("DATA_FILE");
        ReadableFile readableFile = new ReadFileFiles(FILE_PATH);
        WriteableFile writeableFile = new WriteFileFiles(FILE_PATH);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        ParserFromJson<Models> parser = new ParserFromJsonJacksonImpl(mapper);
        String jsonContent = readableFile.getContent();
        Models models = parser.getModels(jsonContent);
        CollectionsManager collectionsManager = new CollectionsManager(models);
        Map<Long, Route> routeMap = collectionsManager.getRouteMap();

        long key = 1;

        System.out.println(models.getType());

    }
}