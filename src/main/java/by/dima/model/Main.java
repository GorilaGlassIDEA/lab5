package by.dima.model;

import by.dima.model.commands.CommandManager;
import by.dima.model.commands.impl.ExecuteScriptCommand;
import by.dima.model.common.AnswerDTO;
import by.dima.model.io.Creatable;
import by.dima.model.io.CreateFileFiles;
import by.dima.model.io.ReadFileBufferedReader;
import by.dima.model.io.ReadableFile;
import by.dima.model.logging.factory.LoggerWrapper;
import by.dima.model.parser.DeserializableAnswerDTO;
import by.dima.model.parser.RouteParserToJson;
import by.dima.model.parser.SerializableCommandDTO;
import by.dima.model.request.ClientRequestUDP;
import by.dima.model.request.Clientable;
import by.dima.model.route.builder.ScannerBuildRoute;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {


    public static void main(String[] args) throws IOException {
        Logger logger = LoggerWrapper.getLogger();
        String filePath = System.getProperty("user.dir") + "/execute.txt";
        Creatable creatable = new CreateFileFiles();
        try {

            if (!Files.exists(Path.of(filePath))) {
                creatable.fileCreator(filePath);
                logger.log(Level.FINEST, "Создан файл по пути: " + filePath);
            }


            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());

            RouteParserToJson parserToJson = new RouteParserToJson(mapper);
            ReadableFile readableFile = new ReadFileBufferedReader();
            Scanner scanner = new Scanner(System.in);

            Long userId = inputLong();

            Clientable clientable = new ClientRequestUDP(userId);
            CommandManager manager = new CommandManager(readableFile, filePath, parserToJson, clientable.getUserId(), logger);

            Client client = new Client(logger, clientable, new SerializableCommandDTO(), new DeserializableAnswerDTO(), manager);
            System.out.println("Клиент запущен!");
            String command = scanner.nextLine();
            while (!command.equals("exit")) {
                command = command.strip();
                System.out.println("Команда которая отправлена: " + command);
                AnswerDTO answerDTO = client.sendCommandReceiveAnswer(command);
                System.out.println(answerDTO.getAnswer());
                command = scanner.nextLine();
            }
            System.out.println("Работа завершена!");

        } catch (RuntimeException e) {

        } finally {
            for (Handler handler : logger.getHandlers()) {
                handler.close();
            }
        }

    }

    public static Long inputLong() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите свой userId (Long)");
        while (true) {
            String userId = scanner.nextLine();
            try {
                Long userIdLong = Long.parseLong(userId);
                System.out.println("Ваш id равен " + userIdLong);
                return userIdLong;
            } catch (NumberFormatException e) {
                System.out.println("Попробуйте еще раз!");
            }
        }
    }
}