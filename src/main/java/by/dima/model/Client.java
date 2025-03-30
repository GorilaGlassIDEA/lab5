package by.dima.model;

import by.dima.model.request.ClientRequestUDP;
import by.dima.model.request.Clientable;

import java.io.IOException;

public class Client {

    public static void start() throws IOException {

        ClientRequestUDP client = new ClientRequestUDP();
        client.say();
    }
}
