package by.dima.model.telegram;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

@PropertySource("classpath:application.properties")
public class ServerGrpc implements AutoCloseable {
    @Value("${this.opened.client.port}")
    private final Integer port = 1230;
    private final BindableService service;
    private Server server;

    public ServerGrpc(BindableService service) {
        this.service = service;
    }


    public void startServer() throws IOException, InterruptedException {
        server = ServerBuilder
                .forPort(port)
                .addService(service)
                .build().start();
        System.out.println("GRPC server запущен на порту: " + port);
        server.awaitTermination();
    }


    @Override
    public void close() {
        if (server != null) {
            server.shutdown();
        }
    }
}
