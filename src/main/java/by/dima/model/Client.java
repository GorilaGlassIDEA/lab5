package by.dima.model;

import by.dima.model.common.CommandDTO;
import by.dima.model.request.ClientRequestUDP;
import by.dima.model.request.Clientable;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.logging.*;

@Setter
@Getter
public class Client {
    private Logger logger;


    public void start() throws IOException {
        CommandDTO commandDTO = new CommandDTO("show");
        Clientable clientRequestUDP = new ClientRequestUDP();

        byte[] buffer = null;

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(commandDTO);
            buffer = bos.toByteArray();
            logger.log(Level.INFO, "Пакет ушел на сервер!");
        } catch (NotSerializableException e) {
            logger.log(Level.WARNING, "Класс CommandDTO не сериализуется!");
        }
        clientRequestUDP.makePost(buffer);
    }
}
