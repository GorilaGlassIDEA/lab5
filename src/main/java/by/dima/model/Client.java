package by.dima.model;

import by.dima.model.dto.CommandDTO;
import by.dima.model.request.ClientRequestUDP;
import by.dima.model.request.Clientable;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    private static Logger logger = Logger.getLogger(Client.class.getName());

    public static void start() throws IOException {
        logger.setLevel(Level.FINE);
        CommandDTO commandDTO = new CommandDTO("insert");
        Clientable clientRequestUDP = new ClientRequestUDP();

        byte[] buffer = null;

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(commandDTO);
            buffer = bos.toByteArray();
        } catch (NotSerializableException e) {
            logger.log(Level.WARNING, "Класс CommandDTO не сериализуется!");
        }
        clientRequestUDP.makePost(buffer);
    }
}
