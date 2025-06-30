package by.dima.model.telegram;

import io.grpc.BindableService;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class App {
    public static void main(String[] args) throws Exception {

        RequestManager requestManager = new RequestManager();
        BindableService service = new MessageExchangeService(requestManager);
        try (ServerGrpc serverGrpc = new ServerGrpc(service)) {
            serverGrpc.startServer();
        } catch (IOException e) {
            System.out.println("Сервер не смог запуститься!");
            log.warn(e.getMessage());
        }

    }
}
