package by.dima.model;

import by.dima.model.service.files.worker.write.WriteFileFiles;
import by.dima.model.service.files.worker.write.WriteableFile;


public class Main {
    public static void main(String[] args) {
//
//        CommandManager commandManager = new CommandManager();
//        commandManager.executeCommand(args);
//
//        Route route = new Route(
//                "name",
//                new Coordinates(10, 10.3),
//                ZonedDateTime.now(),
//                new LocationFrom(10, 10f, "this Name"),
//                new LocationTo(10d, 10d, "name"),
//                10.0
//        );
//
//        Coordinates coordinates = new Coordinates(10, 10d);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//
//
//        ParserToJson<Coordinates> parserCoordinates = new ParserToJsonJacksonImpl<>(objectMapper);
//        System.out.println(parserCoordinates.getJson(new Model<>(coordinates)));
//
//        ParserToJson<Route> parserRoute = new ParserToJsonJacksonImpl<>(objectMapper);
//        System.out.println(parserRoute.getJson(new Model<>(route)));
//        ReadableFile readableFile = new ReadFileFiles();
//       // Чтение файла работает, но нужно переписать под BufferedReader
//        System.out.println(readableFile.getContent("/Users/dmitrijmartynov/IdeaProjects/lab5/src/main/resources/route.json"));
        // работает запись в файл
        String path = "/Users/dmitrijmartynov/IdeaProjects/lab5/src/main/resources/example.txt";
        WriteableFile writeableFile = new WriteFileFiles(path);
        writeableFile.write("some content)");



    }
}