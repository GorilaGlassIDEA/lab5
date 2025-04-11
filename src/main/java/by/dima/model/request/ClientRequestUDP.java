package by.dima.model.request;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class ClientRequestUDP implements Clientable {
    int port = 80;
    InetAddress host;
    DatagramSocket socket;
    DatagramPacket packet;

    public void say() {
    }

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
        } catch (IOException e) {
            throw new RuntimeException("Не удалось отправить данные с сервера");
        }
    }

    @Override
    public byte[] makeGet() {
        //TODO
        return null;
    }
}
