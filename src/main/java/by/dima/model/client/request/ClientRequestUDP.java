package by.dima.model.client.request;


import lombok.Getter;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.util.Random;

public class ClientRequestUDP implements Clientable {
    private final int serverPort;
    @Getter
    private final Long userId;
    @Getter
    private final Long myPort;
    private final InetAddress host;
    private final DatagramSocket socket;
    private DatagramPacket packet;

    //TODO: сделать порты через файл конфигурации
    public ClientRequestUDP(Long userId) throws IOException {
        host = InetAddress.getLocalHost();
        serverPort = 8932;
        this.userId = userId;
        myPort = new Random().nextLong(1_001, 65_000);
        socket = new DatagramSocket(myPort.intValue());
        socket.setSoTimeout(5000);
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
        byte[] data = new byte[100000];
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
