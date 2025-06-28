package by.dima.model.telegram;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;


@Component
@PropertySource("classpath:application.properties")
public class ServerGrpc {
    @Value("${server.grpc.port}")
    private Integer port;
    private BindableService service;

    @Autowired
    public ServerGrpc(@Qualifier("messageExchangeService") BindableService service) {
        this.service = service;
    }

    Server server;

    @PostConstruct
    public void startServer() throws IOException {
        if (port == null) {
            return;
        }
        server = ServerBuilder
                .forPort(port)
                .addService(service)
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
