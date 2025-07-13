package by.dima.model;

import by.dima.model.client.ClientController;
import by.dima.model.client.parser.ForDeserializable;
import by.dima.model.client.parser.ForSerializableObject;
import by.dima.model.client.parser.RouteParserToJson;
import by.dima.model.client.request.ClientRequestUDP;
import by.dima.model.client.request.Clientable;
import by.dima.model.commands.CommandManager;
import by.dima.model.common.AnswerDTO;
import by.dima.model.common.AuthRequestDTO;
import by.dima.model.common.UserModel;
import by.dima.model.telegram.MessageExchangeService;
import by.dima.model.telegram.RequestManager;
import by.dima.model.telegram.ServerGrpc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.grpc.BindableService;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class Client {
    public static void main(String[] args) throws Exception {

        UserModel userModel = new UserModel("test", "test");
        Clientable clientable = new ClientRequestUDP();
        ForSerializableObject<AuthRequestDTO> forSerializableObject = new ForSerializableObject<>();
        ForDeserializable<AnswerDTO> forDeserializable = new ForDeserializable<>();
        CommandManager manager = new CommandManager(new RouteParserToJson(new ObjectMapper().registerModule(new JavaTimeModule())), 1L);

        ClientController controller = new ClientController(userModel, clientable, forSerializableObject, forDeserializable, manager);
        RequestManager requestManager = new RequestManager(controller);
        BindableService service = new MessageExchangeService(requestManager);
        try (ServerGrpc serverGrpc = new ServerGrpc(service)) {
            serverGrpc.startServer();
        } catch (IOException e) {
            System.out.println("Сервер не смог запуститься!");
            log.warn(e.getMessage());
        }
    }
}
