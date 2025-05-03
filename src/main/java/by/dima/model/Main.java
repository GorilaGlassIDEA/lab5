package by.dima.model;

import by.dima.model.commands.CommandManager;
import by.dima.model.common.AnswerDTO;
import by.dima.model.logging.factory.LoggerWrapper;
import by.dima.model.parser.DeserializableAnswerDTO;
import by.dima.model.parser.RouteParserToJson;
import by.dima.model.parser.SerializableCommandDTO;
import by.dima.model.request.ClientRequestUDP;
import by.dima.model.request.Clientable;
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
            Clientable clientable = new ClientRequestUDP();
            CommandManager manager = new CommandManager(parserToJson, clientable.getUserId(), logger);

            Client client = new Client(logger, clientable, new SerializableCommandDTO(), new DeserializableAnswerDTO(), manager);
            System.out.println("Ваш userId для текущей сессии: " + client.getUserId());

            Scanner scanner = new Scanner(System.in);
            while (true) {
                String command = scanner.nextLine();
                command = command.strip();
                System.out.println("Команда которая отправлена: " + command);
                AnswerDTO answerDTO = client.sendCommandReceiveAnswer(command);
                System.out.println(answerDTO.getAnswer());
            }

        } catch (
                RuntimeException e) {

        } finally {
            for (Handler handler : logger.getHandlers()) {
                handler.close();
            }
        }

    }
}