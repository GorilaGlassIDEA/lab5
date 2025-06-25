package by.dima.model.telegram;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;


@Configuration
@PropertySource("classpath:application.properties")
public class ServerGrpc {
    @Value("${server.grpc.port}")
    private Integer port;


    Server server;

    @PostConstruct
    public void startServer() throws IOException {
        if (port == null) {
            return;
        }
        server = ServerBuilder
                .forPort(port)
                .addService(new GreetingService())
                .build().start();
        System.out.println("GRPC server запущен на порту: " + port);


        Thread awaitThread = new Thread(() -> {
            try {
                server.awaitTermination();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        awaitThread.setDaemon(false);
        awaitThread.start();

    }

    @PreDestroy
    public void stopServer() {
        if (server != null) {
            server.shutdown();
        }
    }
}
