package by.dima.model;


import java.io.IOException;

public class Main {
    public static String FILE_PATH;

    public static void main(String[] args) throws IOException{
        Client.start();
//
//        if (System.getenv("FILE_PATH") == null) {
//            FILE_PATH = System.getProperty("user.dir") + '/' + "save";
//        } else {
//            FILE_PATH = System.getenv("FILE_PATH") + '/' + "save";
//        }
//        System.out.println("Путь сохранения вашего файла: " + FILE_PATH);
//        WriteableFile writeableFile = new WriteFileOutputStreamWriter(FILE_PATH);
//        Creatable creatable = new CreateFile(writeableFile);
//        creatable.fileCreator();
//        ReadableFile readableFile = new ReadFileBufferReader(FILE_PATH);
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
//        mapper.registerModule(new JavaTimeModule());
//        ParserFromJson<Models> parserFromJson = new ParserFromJsonJacksonImpl(mapper);
//        ParserToJson parserToJson = new ParserToJsonJacksonImpl(mapper);
//
//        try {
//            String jsonContent = readableFile.getContent();
//
//            Models models = parserFromJson.getModels(jsonContent);
//
//
//            CollectionController collectionController = new CollectionController(models, writeableFile, parserToJson);
//            IdGenerateble idGenerateble = new IdGenerateMy(collectionController);
//            CreateRouteUsingScanner routeCreator = new CreateRouteUsingScanner();
//
//            ScannerWrapper scannerWrapper = new ScannerWrapper();
//            CommandManager manager = new CommandManager(collectionController, scannerWrapper, routeCreator, parserToJson, idGenerateble);
//
//
//
//            try {
//                while (true) {
//                    manager.executeCommand();
//                }
//
//            } catch (NoSuchElementException e) {
//                System.err.println("Program stopped!");
//            }
//
//        } catch (IOException e) {
//            System.err.println("Не удалось получить путь для сохранения объектов!");
//        }
    }
}