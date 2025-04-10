package by.dima.model;

import by.dima.model.dto.CommandDTO;
import by.dima.model.request.ClientRequestUDP;
import by.dima.model.request.Clientable;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.util.logging.*;

@Setter
public class Client {
    private Logger logger;


    public void start() throws IOException {
        CommandDTO commandDTO = new CommandDTO("insert");
        Clientable clientRequestUDP = new ClientRequestUDP();

        byte[] buffer = null;

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(commandDTO);
            buffer = bos.toByteArray();
            logger.log(Level.INFO, "Пакет ушел на сервер!");
        } catch (NotSerializableException e) {
            logger.log(Level.WARNING, "Класс CommandDTO не сериализуется!");
        } finally {
            for (Handler handler : logger.getHandlers()) {
                handler.close();
            }
        }
        clientRequestUDP.makePost(buffer);
    }

}
