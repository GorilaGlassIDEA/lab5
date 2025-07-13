package by.dima.model;

import by.dima.model.client.ClientController;
import by.dima.model.telegram.MessageExchangeService;
import by.dima.model.telegram.RequestManager;
import by.dima.model.telegram.ServerGrpc;
import io.grpc.BindableService;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class Client {
    public static void main(String[] args) throws Exception {
        ClientController controller = new ClientController()
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
