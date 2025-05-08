package by.dima.model.client.request;

import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;

public interface Clientable {
    default Long getUserId() {
        return -1L;
    }

    void makePost(byte[] data) throws SocketTimeoutException;

    ByteBuffer makeGet() throws SocketTimeoutException;

}
