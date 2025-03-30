package by.dima.model.request;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ClientRequestUDP implements Clientable {


    @Override
    public void makePost(byte[] data) {
        try {
        DatagramSocket datagramSocket = new DatagramSocket();
        int len = data.length;
            DatagramPacket datagramPacket = new DatagramPacket(data, len, InetAddress.getLocalHost(), 80);
            datagramSocket.send(datagramPacket);
        } catch (IOException e) {
            System.err.println("Не удалость отпарвить пакет с данными!");
        }
    }

    @Override
    public byte[] makeGet() {

        return null;
    }
}
