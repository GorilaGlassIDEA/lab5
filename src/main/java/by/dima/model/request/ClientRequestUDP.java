package by.dima.model.request;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientRequestUDP implements Clientable {


    public void say() throws IOException {

        byte[] dataOutput = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int port = 80;
        InetAddress host = InetAddress.getLocalHost();
        DatagramSocket socket;
        DatagramPacket packet;

        socket = new DatagramSocket(100);
        packet = new DatagramPacket(dataOutput, dataOutput.length, host, port);
        socket.send(packet);

        dataOutput = new byte[10];
        packet = new DatagramPacket(dataOutput, dataOutput.length);
        socket.receive(packet);

    }

    @Override
    public void makePost(byte[] data) {


    }

    @Override
    public byte[] makeGet() {
        return null;
    }
}
