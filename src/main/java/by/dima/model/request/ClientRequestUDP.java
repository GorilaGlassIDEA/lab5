package by.dima.model.request;


import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;

@Component
public class ClientRequestUDP implements Clientable {
    private final int serverPort;
    private final int myPort;
    private final InetAddress host;
    private final DatagramSocket socket;
    private DatagramPacket packet;

    //TODO: сделать порты через файл конфигурации
    public ClientRequestUDP() throws IOException {
        host = InetAddress.getLocalHost();
        serverPort = 6676;
        myPort = 1000;
        socket = new DatagramSocket(myPort);
    }

    @Override
    public void makePost(byte[] data) {
        try {
            packet = new DatagramPacket(data, data.length, host, serverPort);
            socket.send(packet);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось отправить данные с сервера");
        }
    }

    @Override
    public ByteBuffer makeGet() {
        byte[] data = new byte[1024];
        ByteBuffer buffer;
        try {
            packet = new DatagramPacket(data, data.length);
            socket.receive(packet);
            buffer = ByteBuffer.wrap(data, 0, packet.getLength());
            return buffer;
        } catch (IOException e) {
            //TODO: прокинуть Logger
            return null;
        }
    }
}
