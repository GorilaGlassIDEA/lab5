package by.dima.model;

import by.dima.model.commands.CommandManager;
import by.dima.model.common.AnswerDTO;
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
import java.util.Scanner;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class Main {


    public static void main(String[] args) throws IOException {
        Logger logger = LoggerWrapper.getLogger();
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());

            RouteParserToJson parserToJson = new RouteParserToJson(mapper);
            Scanner scanner = new Scanner(System.in);

            Long userId = inputLong();

            Clientable clientable = new ClientRequestUDP(userId);
            CommandManager manager = new CommandManager(parserToJson, clientable.getUserId(), logger);

            Client client = new Client(logger, clientable, new SerializableCommandDTO(), new DeserializableAnswerDTO(), manager);
            System.out.println("Клиент запущен!");

            while (true) {
                String command = scanner.nextLine();
                command = command.strip();
                System.out.println("Команда которая отправлена: " + command);
                AnswerDTO answerDTO = client.sendCommandReceiveAnswer(command);
                System.out.println(answerDTO.getAnswer());
            }

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