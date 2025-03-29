package by.dima.model.request;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ClientRequestUDP implements Clientable {

    private final DatagramSocket datagramSocket;

    public ClientRequestUDP(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    @Override
    public void makePost(byte[] data) {
        int len = data.length;
        DatagramPacket datagramPacket = new DatagramPacket(data, len, datagramSocket.getInetAddress(), 1080);
        try{
            datagramSocket.send(datagramPacket);
        }catch (IOException e){
            System.err.println("Не удалость отпарвить пакет с данными!");
        }
    }

    @Override
    public byte[] makeGet() {

        return null;
    }
}
