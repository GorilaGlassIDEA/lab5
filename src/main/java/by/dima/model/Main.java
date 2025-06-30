package by.dima.model;

import by.dima.model.auth.AuthScanner;
import by.dima.model.auth.AuthService;
import by.dima.model.auth.mode.CLIMode;
import by.dima.model.auth.mode.ChooseAuthMode;
import by.dima.model.client.Client;
import by.dima.model.commands.CommandManager;
import by.dima.model.common.AnswerDTO;
import by.dima.model.common.AuthList;
import by.dima.model.common.AuthRequestDTO;
import by.dima.model.common.UserModel;
import by.dima.model.util.PasswordHasher;
import by.dima.model.util.RequestFacade;
import by.dima.model.util.io.Creatable;
import by.dima.model.util.io.CreateFileFiles;
import by.dima.model.util.io.ReadFileBufferedReader;
import by.dima.model.util.io.ReadableFile;
import by.dima.model.util.logger.factory.LoggerWrapper;
import by.dima.model.client.parser.ForDeserializableAnswerDTO;
import by.dima.model.client.parser.RouteParserToJson;
import by.dima.model.client.parser.ForSerializableObject;
import by.dima.model.client.request.ClientRequestUDP;
import by.dima.model.client.request.Clientable;
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
        String filePath = System.getProperty("user.dir") + "/execute.json";
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

            Clientable clientable = new ClientRequestUDP();

            //переписать все под RequestFacade
            RequestFacade requestFacade = new RequestFacade(new ForDeserializableAnswerDTO<>(), new ForSerializableObject<>(), clientable);

            AuthService authService = new AuthService(requestFacade);
            System.out.println("(Регистрация - 0, Вход в систему - 1");

            ChooseAuthMode chooseAuthMode = new CLIMode(scanner, authService);
            UserModel userModel = chooseAuthMode.getAnswer();


            if (userModel.getId() == null) {
                userModel.setId(-1);
            }

            System.out.println(userModel);

            CommandManager manager = new CommandManager(mapper, readableFile, filePath, parserToJson, (long) userModel.getId(), logger);
            Client client = new Client(userModel, logger, clientable, new ForSerializableObject<>(), new ForDeserializableAnswerDTO<>(), manager);


            System.out.println("Клиент запущен! Введите команду: ");
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
            System.out.println("Превышено время ожидания данных от сервера!");
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

    public static UserModel authorizedStatusControl(AuthScanner authScanner, AuthService authService) {
        UserModel userModel = authScanner.inputUserDataFromKeyboard();
        userModel.setPassword(PasswordHasher.hashPasswordSHA1(userModel.getPassword()));
        AuthRequestDTO authRequestDTO = authService.authorization(userModel);
        while (authRequestDTO.getAuthList() != AuthList.AUTHORIZATION) {
            userModel = authScanner.inputUserDataFromKeyboard();
            userModel.setPassword(PasswordHasher.hashPasswordSHA1(userModel.getPassword()));
            authRequestDTO = authService.authorization(userModel);
        }
        System.out.println("Класс Main метод авторизации" + authRequestDTO);
        return userModel;
    }

    public static UserModel authenticationStatusControl(AuthScanner authScanner, AuthService authService) {
        UserModel userModel = authScanner.inputUserDataFromKeyboard();
        userModel.setPassword(PasswordHasher.hashPasswordSHA1(userModel.getPassword()));
        AuthRequestDTO authRequestDTO = authService.authentication(userModel);

        while (authRequestDTO.getAuthList() != AuthList.AUTHORIZATION) {
            userModel = authScanner.inputUserDataFromKeyboard();
            userModel.setPassword(PasswordHasher.hashPasswordSHA1(userModel.getPassword()));
            authRequestDTO = authService.authentication(userModel);
        }
        return userModel;
    }
}

