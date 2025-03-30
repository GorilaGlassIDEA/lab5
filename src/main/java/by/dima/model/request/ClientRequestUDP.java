package by.dima.model.request;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientRequestUDP implements Clientable {
    byte[] dataOutput = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int port = 80;
    InetAddress host;
    DatagramSocket socket;
    DatagramPacket packet;

    public ClientRequestUDP() throws IOException {
        host = InetAddress.getLocalHost();
        socket = new DatagramSocket();
    }

    @Override
    public void makePost(byte[] data) {
        try {
            socket = new DatagramSocket();
            packet = new DatagramPacket(data, data.length, host, port);
            socket.send(packet);
            System.out.println("Запрос отправлен!");
        } catch (IOException e) {
            throw new RuntimeException("Не удалось отправить данные с сервера");
        }
    }

    @Override
    public byte[] makeGet() {

        try {
            dataOutput = new byte[10];
            packet = new DatagramPacket(dataOutput, dataOutput.length);
            socket.receive(packet);
            System.out.println("Запрос принят!");
            return dataOutput;
        } catch (IOException e) {
            throw new RuntimeException("Не удалось получить данные с сервера!");
        }
    }
}
