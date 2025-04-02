package by.dima.model.request;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;

public class ClientRequestTCP implements Clientable {

    private byte[] testByte = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    public ClientRequestTCP() {

    }

    public void say() throws IOException {
        int port = 80;
        InetAddress host = InetAddress.getLocalHost();
        InputStream is;
        OutputStream os;
        Socket socket;


        socket = new Socket(host, port);

        os = socket.getOutputStream();
        os.write(testByte);

        is = socket.getInputStream();
        is.read(testByte);

        System.out.println("Ответ получен " + Arrays.toString(testByte));
    }

    @Override
    public void makePost(byte[] data) {

    }

    @Override
    public byte[] makeGet() {
        return new byte[0];
    }
}
