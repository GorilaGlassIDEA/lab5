package by.dima.model.service.files.parser.string.impl;

import by.dima.model.data.route.model.main.Route;
import by.dima.model.data.route.model.sub.Coordinates;
import by.dima.model.data.route.model.sub.LocationFrom;
import by.dima.model.data.route.model.sub.LocationTo;
import by.dima.model.service.files.parser.string.model.ParserFromJson;
import by.dima.model.service.files.parser.string.model.ParserToJson;
import by.dima.model.service.files.worker.read.ReadFileFiles;
import by.dima.model.service.files.worker.read.ReadableFile;
import by.dima.model.service.files.worker.write.WriteFileFiles;
import by.dima.model.service.files.worker.write.WriteableFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Test;

import java.time.ZonedDateTime;

public class ParserFromJsonJacksonImplTest {
    @Test
    public void testGetContentTest() {
        ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        Route route = new Route(
                10,
                "Dima",
                new Coordinates(10, 10d),
                new LocationFrom(10d, 10f, "This coordinates"),
                new LocationTo(10d, 10d, "That coordinates"),
                10d);

        WriteableFile writeableFile = new WriteFileFiles("/Users/dmitrijmartynov/IdeaProjects/lab5/src/main/resources/route.json");
        ParserToJson parser = new ParserToJsonJacksonImpl(mapper);
        writeableFile.write(parser.getJson(route));


        ReadableFile readableFile = new ReadFileFiles();
        String content = readableFile.getContent("/Users/dmitrijmartynov/IdeaProjects/lab5/src/main/resources/route.json");


        ParserFromJson<Route> parserFrom = new ParserFromJsonJacksonImpl(mapper);
        Route route1 = parserFrom.getModel(content);
        System.out.println(route1.getCreationDate());
    }
}