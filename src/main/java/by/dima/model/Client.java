package by.dima.model;

import by.dima.model.request.ClientRequestTCP;
import by.dima.model.request.ClientRequestUDP;
import by.dima.model.request.Clientable;

import java.io.IOException;
import java.nio.charset.Charset;

public class Client {

    public static void start() throws IOException {

        Clientable client = new ClientRequestTCP();
        client.say();
//        client.makePost("Hello".getBytes());
//        byte[] data = client.makeGet();
//        System.out.println(new String(data, Charset.defaultCharset()));

    }
}
